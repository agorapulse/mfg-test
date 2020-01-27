package com.agorapulse.mfg.test

import com.amazonaws.services.lambda.runtime.events.ScheduledEvent
import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors
import io.micronaut.function.FunctionBean
import io.micronaut.function.executor.FunctionInitializer
import java.util.function.Function
import javax.inject.Inject

@CompileStatic
@FunctionBean(
        value = 'mfg-test',
        method = 'apply'
)
@InheritConstructors
class MfgTestHandler extends FunctionInitializer implements Function<ScheduledEvent, String> {

    /**
     * This main method allows running the function as a CLI application using: echo '{}' | java -jar function.jar
     * where the argument to echo is the JSON to be parsed.
     */
    static void main(String...args) throws IOException {
        MfgTestHandler function = new MfgTestHandler()
        function.run(args) { context ->
            function.apply(context.get(ScheduledEvent))
        }
    }

    @Inject MfgTestService mfgTestService

    @Override
    String apply(ScheduledEvent event) {
         return mfgTestService.handle(event)
    }

}
