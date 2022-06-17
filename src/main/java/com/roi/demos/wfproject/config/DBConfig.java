package com.roi.demos.wfproject.config;

import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import io.r2dbc.spi.Option;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.r2dbc.ConnectionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;

@Configuration
@EnableR2dbcRepositories
public class DBConfig extends AbstractR2dbcConfiguration {

    @Bean("connectFactory")
    @Override
    public ConnectionFactory connectionFactory() {

//      return new H2ConnectionFactory(H2ConnectionConfiguration.builder()
//                .inMemory("demo")
//                .property(H2ConnectionOption.DB_CLOSE_DELAY,"-1")
//                .property(H2ConnectionOption.DB_CLOSE_ON_EXIT,"false")
//                .build());

        return ConnectionFactoryBuilder.withOptions(ConnectionFactoryOptions.builder()
                .option(DRIVER,"h2")
                .option(PROTOCOL, "mem")
                .option(DATABASE, "mem")
                .option(Option.valueOf("ACCESS_MODE_DATA"),"HSQLDB")
                .option(Option.valueOf("DB_CLOSE_DELAY"),"-1")
                .option(Option.valueOf("DB_CLOSE_ON_EXIT"),"false")).build();
    }

    @Bean("dbInit")
    @DependsOn("connectFactory")
    public ConnectionFactoryInitializer initDbContent(ConnectionFactory cxf){
        ConnectionFactoryInitializer cxInit = new ConnectionFactoryInitializer();
        cxInit.setConnectionFactory(cxf);

        CompositeDatabasePopulator dbInit = new CompositeDatabasePopulator();
        dbInit.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("h2_schema.sql")));
        dbInit.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("corp_events.sql")));
        dbInit.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("person.sql")));

        cxInit.setDatabasePopulator(dbInit);

        return cxInit;
    }

}
