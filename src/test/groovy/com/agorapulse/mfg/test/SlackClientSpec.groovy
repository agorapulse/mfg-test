package com.agorapulse.mfg.test

import com.stehno.ersatz.ContentType
import com.stehno.ersatz.ErsatzServer
import groovy.json.JsonSlurper
import io.micronaut.context.ApplicationContext
import spock.lang.AutoCleanup
import spock.lang.Specification

class SlackClientSpec extends Specification {

    @AutoCleanup ErsatzServer server = new ErsatzServer({
        decoder('application/json') { content, context ->
            new JsonSlurper().parse(content ?: '{}'.bytes)
        }
        reportToConsole()
    })
    @AutoCleanup ApplicationContext context

    SlackClient client

    void setup() {
        server.expectations {
            post('/slackapp/slackkey/slacksecret') {
                body([text: 'Hello'], ContentType.APPLICATION_JSON)
                responds().body('ok', ContentType.TEXT_PLAIN)
            }
        }.start()

        context = ApplicationContext.build([
                'slack.url': server.httpUrl,
                'slack.app': 'slackapp',
                'slack.key': 'slackkey',
                'slack.secret': 'slacksecret',
            ], 'test').build()
        context.start()

        client = context.getBean(SlackClient)
    }

    void 'handle event'() {
        when:
            String response = client.postMessage('Hello')
        then:
            response == 'ok'

            server.verify()
    }

}
