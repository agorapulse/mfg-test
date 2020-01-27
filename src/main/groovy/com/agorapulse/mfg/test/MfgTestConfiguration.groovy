package com.agorapulse.mfg.test

import groovy.transform.CompileStatic
import groovy.transform.ToString
import io.micronaut.context.annotation.ConfigurationProperties

@ToString
@CompileStatic
@ConfigurationProperties('mfg.test')
class MfgTestConfiguration {

    String value

}
