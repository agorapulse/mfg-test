package com.agorapulse.mfg.test

import com.agorapulse.micronaut.aws.s3.SimpleStorageService
import com.agorapulse.micronaut.aws.ses.SimpleEmailService
import com.agorapulse.micronaut.aws.sts.SecurityTokenService
import com.agorapulse.micronaut.facebooksdk.FacebookApplication
import com.agorapulse.micronaut.log4aws.LogError
import com.agorapulse.micronaut.snitch.Snitch
import com.amazonaws.services.cloudwatch.AmazonCloudWatch
import com.amazonaws.services.lambda.runtime.events.ScheduledEvent
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import javax.inject.Singleton

@Slf4j
@Singleton
@CompileStatic
class MfgTestService {

    private final MfgTestConfiguration configuration
    private final SlackClient slackClient
    private final DynamoDBEntityDBService dynamoDBEntityService
    private final SimpleKinesisClient kinesisClient
    private final SimpleStorageService s3
    private final SimpleEmailService ses
    private final SimpleNotificationClient notificationClient
    private final SimpleQueueClient queueClient
    private final SecurityTokenService sts
    private final FacebookApplication fb

    @SuppressWarnings('ParameterCount')
    MfgTestService(
        MfgTestConfiguration configuration,
        SlackClient slackClient,
        DynamoDBEntityDBService dynamoDBEntityService,
        SimpleKinesisClient kinesisClient,
        SimpleStorageService s3,
        SimpleEmailService ses,
        SimpleNotificationClient notificationClient,
        SimpleQueueClient queueClient,
        SecurityTokenService sts,
        FacebookApplication fb
    ) {
        this.configuration = configuration
        this.slackClient = slackClient
        this.dynamoDBEntityService = dynamoDBEntityService
        this.kinesisClient = kinesisClient
        this.s3 = s3
        this.ses = ses
        this.notificationClient = notificationClient
        this.queueClient = queueClient
        this.sts = sts
        this.fb = fb
    }

    @Snitch @LogError
    String handle(ScheduledEvent event) {
        // TODO: implement
        log.debug "got event: $event"
        throw new UnsupportedOperationException('TODO: implement')
    }

}
