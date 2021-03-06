package com.agorapulse.mfg.test

import groovy.sql.Sql
import io.micronaut.context.ApplicationContext
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.spock.Testcontainers
import spock.lang.Shared
import spock.lang.Specification

import javax.sql.DataSource

@Testcontainers
class PostgreSQLSpec extends Specification {

    @Shared
    PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer()
            .withDatabaseName('foo')
            .withUsername('foo')
            .withPassword('secret')

    DataSource dataSource

    void setup() {
        ApplicationContext context = ApplicationContext.build(
                'datasources.default.url': postgreSQLContainer.jdbcUrl,
                'datasources.default.username': 'foo',
                'datasources.default.password': 'secret',
                'datasources.default.driverClassName': 'org.postgresql.Driver',
                'test'
        ).build()
        context.start()

        dataSource = context.getBean(DataSource)
    }

    void 'database is accessible'() {
        given:
            Sql sql = new Sql(dataSource)
        expect:
            sql.rows('SELECT 1')[0][0] == 1
    }

}
