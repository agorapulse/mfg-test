package com.agorapulse.mfg.test

import com.amazonaws.services.lambda.runtime.events.ScheduledEvent
import spock.lang.AutoCleanup
import spock.lang.Specification

class MfgTestHandlerSpec extends Specification {

    MfgTestService service = Mock(MfgTestService)

    @AutoCleanup MfgTestHandler handler = new MfgTestHandler()

    void setup() {
        handler.mfgTestService = service
    }

    void 'handle event'() {
        given:
            ScheduledEvent event = new ScheduledEvent()
            String expectedOutput = '8f223bea-3e80-41ff-b25f-67885d25bb87'
        when:
            String output = handler.apply(event)
        then:
            output.is expectedOutput

            1 * service.handle(event) >> expectedOutput
    }

}
