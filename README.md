# Mfg Test

This is a Micronaut AWS Lambda Function generated using [Micronaut Function Template][mfgt]

<!-- TODO: add your own description -->

[mfgt]: https://github.com/agorapulse/lazybones-templates#micronaut-function-groovy

## Getting Around

<!-- Feel free to delete this section when you get familiar with the project -->

The following files are waiting for you implementation:

 * [MfgTestService](src/main/groovy/com/agorapulse/mfg/test/MfgTestService.groovy) - The main service
 * [MfgTestConfiguration](src/main/groovy/com/agorapulse/mfg/test/MfgTestConfiguration.groovy) - The configuration object
 * [MfgTestServiceSpec](src/test/groovy/com/agorapulse/mfg/test/MfgTestServiceSpec.groovy) - The main service's test



The following files were added as the examples by the libraries applied:
 * [AwsSpec](src/test/groovy/com/agorapulse/mfg/test/AwsSpec.groovy) - Example mock AWS interactions test using Localstack
 * [DynamoDBEntity](src/main/groovy/com/agorapulse/mfg/test/DynamoDBEntity.groovy) - Example DynamoDB entity
 * [DynamoDBEntityDBService](src/main/groovy/com/agorapulse/mfg/test/DynamoDBEntityDBService.groovy) - Example declarative DynamoDB data service
 * [DynamoDBEntitySpec](src/test/groovy/com/agorapulse/mfg/test/DynamoDBEntitySpec.groovy) - Example test with Dru DynamoDB client
 * [FixtBasedSpec](src/test/groovy/com/agorapulse/mfg/test/FixtBasedSpec.groovy) - Example test with fixtures
 * [MySQLSpec](src/test/groovy/com/agorapulse/mfg/test/MySQLSpec.groovy) - Example test using real MySQL server running inside Docker
 * [PostgreSQLSpec](src/test/groovy/com/agorapulse/mfg/test/PostgreSQLSpec.groovy) - Example test using real PostgreSQL server running inside Docker
 * [SimpleKinesisClient](src/main/groovy/com/agorapulse/mfg/test/SimpleKinesisClient.java) - Example declarative Kinesis client
 * [SimpleNotificationClient](src/main/groovy/com/agorapulse/mfg/test/SimpleNotificationClient.java) - Example declarative SNS client
 * [SimpleQueueClient](src/main/groovy/com/agorapulse/mfg/test/SimpleQueueClient.java) - Example declarative SQS client
 * [SlackClient](src/main/groovy/com/agorapulse/mfg/test/SlackClient.java) - Example HTTP client for Slack
 * [SlackClientSpec](src/test/groovy/com/agorapulse/mfg/test/SlackClientSpec.groovy) - Example HTTP client test using Ersatz
 * [enities.json](src/test/resources/com/agorapulse/mfg/test/DynamoDBEntitySpec/entities.json) - Example JSON entities fixture file
 * [hello.txt](src/test/resources/com/agorapulse/mfg/test/FixtBasedSpec/hello.txt) - Example fixture file

The following files are infrastructure ones and should not be changed:
 * [Application](src/main/groovy/com/agorapulse/mfg/test/Application.java) - Local server launcher
 * [MfgTestHandler](src/main/groovy/com/agorapulse/mfg/test/MfgTestHandler.groovy) - AWS Lambda handler
 * [MfgTestHandlerSpec](src/test/groovy/com/agorapulse/mfg/test/MfgTestHandlerSpec.groovy) - AWS Lambda handler sanity test

There are two configuration files:
 * [application.yml](src/main/resources/application.yml) - Main configuration file for production and local server
 * [application-dev.yml](src/main/resources/application-dev.yml) - Main configuration file for local server only




You need to [create new GitHub repository](https://github.com/new) called `agorapulse/mfg-test` if you haven't done so.

You need to configure [GitHub Secrets](https://github.com/agorapulse/mfg-test/settings/secrets) if you want to enable automated deployments:
 * `STAGING_AWS_ACCESS_KEY_ID` - AWS key ID for the staging environment
 * `STAGING_AWS_SECRET_ACCESS_KEY` - AWS secret key for the staging environment
 * `PRODUCTION_AWS_ACCESS_KEY_ID` - AWS key ID for the production environment
 * `PRODUCTION_AWS_SECRET_ACCESS_KEY` - AWS secret key for the production environment

Then tou can push this project to GitHub:
```
git init
git add -A
git commit -m "Initial commit"
git remote add origin git@github.com:agorapulse/mfg-test.git
git push -u origin master
```



## Local Execution

The function can be run using the embedded HTTP server at `http://localhost:8080`:

```
./gradlew run
```

Then you can execute the function using [IntelliJ HTTP Request File](mfg-test.http) or cURL:

```
curl --header "Content-Type: application/json" --request POST --data '{ }' http://localhost:8080/mfg-test
```

The port can be changed by setting the `micronaut.server.port` property in the local [configuration file](src/main/resources/application-dev.yml).

## Manual Deployment

You need to setup you AWS credentials before deploying this function. There are two ways how to achieve this:

Either ensure you have set up your credentials in `~/.aws/credentials` file:
```
[beta]
aws_access_key_id = beta access key ID
aws_secret_access_key = beta secret access key
region = eu-west-1
```

Or by using environmental variables by running following commands in your terminal:

```
export AWS_ACCESS_KEY_ID=beta access key ID
export AWS_SECRET_ACCESS_KEY=beta secret access key
export AWS_DEFAULT_REGION=eu-west-1
```

Then you can deploy the function by running the following command:

```
./gradlew deploy
```

After deployment you should be able to open the following links:

 * [Function][deployed]
 * [Logs][logs]

[deployed]: https://eu-west-1.console.aws.amazon.com/lambda/home?region=eu-west-1#/functions/MfgTest?tab=configuration
[logs]: https://eu-west-1.console.aws.amazon.com/cloudwatch/home?region=eu-west-1#logStream:group=/aws/lambda/MfgTest;streamFilter=typeLogStreamPrefix


## Continuous Integration and Delivery

This function has enabled continuous integration and delivery using GitHub Actions:

 * [Check](https://github.com/agorapulse/mfg-test/actions?query=workflow%3ACheck) - After each commit to any branch or any PR request opened the `./gradlew check` command is run (see [gradle.yml](.github/workflows/gradle.yml)))
 * [Gradle RC Watchdog](https://github.com/agorapulse/mfg-test/actions?query=workflow%3AGradle+RC+Watchdog) - Once month `master` branch is tested against the latest RC version of Gradle``./gradlew check` command is run (see [gradle-versions-watchdog.yml](.github/workflows/gradle-versions-watchdog.yml))
 * [Release to Staging](https://github.com/agorapulse/mfg-test/actions?query=workflow%3ARelease+to+Staging) - Every commit into `master` branch runs `./gradlew deploy` using `STAGING_AWS_ACCESS_KEY_ID` and  `STAGING_AWS_SECRET_ACCESS_KEY` secrets (see [staging.yml.gtpl](.github/workflows/staging.yml))
 * [Release to Production](https://github.com/agorapulse/mfg-test/actions?query=workflow%3ARelease+to+Production) - Every release (tag)  runs `./gradlew deploy` using `PRODUCTION_AWS_ACCESS_KEY_ID` and  `PRODUCTION_AWS_SECRET_ACCESS_KEY` secrets  (see [release.yml](.github/workflows/release.yml))




## Libraries


  * Micronaut HTTP Client - [Documentation][Micronaut HTTP Client]
    * **CONFIGURATION REQUIRED:** - visit [application.yml](src/main/resources/application.yml) configuration file to correct the placeholder values!
  * Micronaut AWS SDK API Gateway WebSockets - [Documentation][Micronaut AWS SDK API Gateway WebSockets]
    * **CONFIGURATION REQUIRED:** - visit [application.yml](src/main/resources/application.yml) configuration file to correct the placeholder values!
  * Micronaut AWS SDK CloudWatch - [Documentation][Micronaut AWS SDK CloudWatch]
  * Micronaut AWS SDK Core - [Documentation][Micronaut AWS SDK Core]
  * Micronaut AWS SDK DynamoDB - [Documentation][Micronaut AWS SDK DynamoDB]
  * Micronaut AWS SDK Kinesis - [Documentation][Micronaut AWS SDK Kinesis]
    * **CONFIGURATION REQUIRED:** - visit [application.yml](src/main/resources/application.yml) configuration file to correct the placeholder values!
  * Micronaut AWS SDK Kinesis Worker - [Documentation][Micronaut AWS SDK Kinesis Worker]
  * Micronaut AWS SDK S3 - [Documentation][Micronaut AWS SDK S3]
    * **CONFIGURATION REQUIRED:** - visit [application.yml](src/main/resources/application.yml) configuration file to correct the placeholder values!
  * Micronaut AWS SDK SES - [Documentation][Micronaut AWS SDK SES]
  * Micronaut AWS SDK SNS - [Documentation][Micronaut AWS SDK SNS]
    * **CONFIGURATION REQUIRED:** - visit [application.yml](src/main/resources/application.yml) configuration file to correct the placeholder values!
  * Micronaut AWS SDK SQS - [Documentation][Micronaut AWS SDK SQS]
    * **CONFIGURATION REQUIRED:** - visit [application.yml](src/main/resources/application.yml) configuration file to correct the placeholder values!
  * Micronaut AWS SDK STS - [Documentation][Micronaut AWS SDK STS]
  * Micronaut Data JDBC - [Documentation][Micronaut Data JDBC]
    * **CONFIGURATION REQUIRED:** - visit [application.yml](src/main/resources/application.yml) configuration file to correct the placeholder values!
  * GORM for Hibernate - [Documentation][GORM for Hibernate]
    * **CONFIGURATION REQUIRED:** - visit [application.yml](src/main/resources/application.yml) configuration file to correct the placeholder values!
  * Facebook SDK for Micronaut - [Documentation][Facebook SDK for Micronaut]
    * **CONFIGURATION REQUIRED:** - visit [application.yml](src/main/resources/application.yml) configuration file to correct the placeholder values!
  * Agorapulse Remember - [Documentation][Agorapulse Remember]
  * Micronaut Snitch - [Documentation][Micronaut Snitch]
    * **CONFIGURATION REQUIRED:** - visit [application.yml](src/main/resources/application.yml) configuration file to correct the placeholder values!
  * Fixt (Test Fixure Loading Helper) - [Documentation][Fixt (Test Fixure Loading Helper)]
  * Gru Tests for HTTP - [Documentation][Gru Tests for HTTP]
  * Gru Tests for API Gateway - [Documentation][Gru Tests for API Gateway]
  * Ersatz Mock Server - [Documentation][Ersatz Mock Server]
  * Dru Tests for DynamoDB - [Documentation][Dru Tests for DynamoDB]
  * Dru Tests for GORM - [Documentation][Dru Tests for GORM]
  * Testcontainers for Localstack (AWS) - [Documentation][Testcontainers for Localstack (AWS)]
  * Testcontainers for PostgreSQL - [Documentation][Testcontainers for PostgreSQL]
  * Testcontainers for MySQL - [Documentation][Testcontainers for MySQL]

Please, follow the links to read more about how to use the library in your project.


[Micronaut HTTP Client]: https://docs.micronaut.io/latest/guide/httpClient.html

[Micronaut AWS SDK API Gateway WebSockets]: https://agorapulse.github.io/micronaut-libraries/#_websockets_for_api_gateway

[Micronaut AWS SDK CloudWatch]: https://agorapulse.github.io/micronaut-libraries/#_aws_sdk_for_micronaut

[Micronaut AWS SDK Core]: https://agorapulse.github.io/micronaut-libraries/#_aws_sdk_for_micronaut

[Micronaut AWS SDK DynamoDB]: https://agorapulse.github.io/micronaut-libraries/#_dynamodb

[Micronaut AWS SDK Kinesis]: https://agorapulse.github.io/micronaut-libraries/#_kinesis

[Micronaut AWS SDK Kinesis Worker]: https://agorapulse.github.io/micronaut-libraries/#_listening_with_kinesislistener

[Micronaut AWS SDK S3]: https://agorapulse.github.io/micronaut-libraries/#_simple_storage_service_s3

[Micronaut AWS SDK SES]: https://agorapulse.github.io/micronaut-libraries/#_simple_email_service_ses

[Micronaut AWS SDK SNS]: https://agorapulse.github.io/micronaut-libraries/#_simple_notification_service_sns

[Micronaut AWS SDK SQS]: https://agorapulse.github.io/micronaut-libraries/#_simple_queue_service_sqs

[Micronaut AWS SDK STS]: https://agorapulse.github.io/micronaut-libraries/#_security_token_service_sts

[Micronaut Data JDBC]: https://micronaut-projects.github.io/micronaut-data/latest/guide/#sql

[GORM for Hibernate]: https://micronaut-projects.github.io/micronaut-groovy/latest/guide/#gorm

[Facebook SDK for Micronaut]: https://github.com/agorapulse/micronaut-facebook-sdk#micronaut-facebook-sdk

[Agorapulse Remember]: https://github.com/agorapulse/remember#remember

[Micronaut Snitch]: https://github.com/agorapulse/micronaut-snitch#micronaut-snitch

[Fixt (Test Fixure Loading Helper)]: https://agorapulse.github.io/testing-libraries/#_fixt

[Gru Tests for HTTP]: https://agorapulse.github.io/gru/#_http

[Gru Tests for API Gateway]: https://agorapulse.github.io/gru/#_aws_api_gateway

[Ersatz Mock Server]: http://stehno.com/ersatz/guide/

[Dru Tests for DynamoDB]: https://agorapulse.github.io/dru/#_dynamodb

[Dru Tests for GORM]: https://agorapulse.github.io/dru/#_gorm

[Testcontainers for Localstack (AWS)]: https://medium.com/agorapulse-stories/how-to-unit-test-aws-services-with-localstack-and-testcontainers-1d39fe5dc6c2

[Testcontainers for PostgreSQL]: https://www.testcontainers.org/modules/databases/postgres/

[Testcontainers for MySQL]: https://www.testcontainers.org/modules/databases/mysql/



