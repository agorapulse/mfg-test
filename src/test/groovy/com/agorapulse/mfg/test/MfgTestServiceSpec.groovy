package com.agorapulse.mfg.test

import com.amazonaws.services.lambda.runtime.events.ScheduledEvent
import io.micronaut.context.ApplicationContext
import spock.lang.AutoCleanup
import spock.lang.Specification

class MfgTestServiceSpec extends Specification {

    @AutoCleanup ApplicationContext context

    MfgTestService service

    void setup() {
        context = ApplicationContext.build('test').build()
        // TODO: register mock collaborators
        // context.registerSingleton(Foo, fooMock)
        context.start()

        service = context.getBean(MfgTestService)
    }

    void 'handle event'() {
        given:
            ScheduledEvent event = new ScheduledEvent()
            // String expectedOutput = '778a3258-4cfc-4d1e-9261-93162f01657d'
        when:
            /* String output = */ service.handle(event)
        then:
            thrown UnsupportedOperationException

            // TODO: verify output
            // output == expectedOutput
    }

}
