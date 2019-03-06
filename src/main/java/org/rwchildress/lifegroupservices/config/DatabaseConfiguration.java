package org.rwchildress.lifegroupservices.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariDataSource;
import org.rwchildress.lifegroupservices.aws.AwsCredentials;
import org.rwchildress.lifegroupservices.aws.AwsSecretsFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.IOException;

import static java.lang.String.format;

@Configuration
public class DatabaseConfiguration {

    private AwsSecretsFinder awsSecretsFinder;
    private ObjectMapper mapper;

    @Value("${datasource.rds.secret.name}")
    private String secretName;

    @Autowired
    public DatabaseConfiguration (AwsSecretsFinder awsSecretsFinder, ObjectMapper mapper) {
        this.awsSecretsFinder = awsSecretsFinder;
        this.mapper = mapper;
    }

    @Bean
    public DataSource dataSource() throws IOException {
        String credsJson = awsSecretsFinder.getSecret(secretName);
        AwsCredentials dbCreds = mapper.readValue(credsJson, AwsCredentials.class);

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setJdbcUrl(format("jdbc:postgresql://%s:%s/%s", dbCreds.getHost(), dbCreds.getPort(), dbCreds.getDbname()));
        dataSource.setUsername(dbCreds.getUsername());
        dataSource.setPassword(dbCreds.getPassword());
        dataSource.setIdleTimeout(30000);
        dataSource.setMinimumIdle(2);
        dataSource.setMaximumPoolSize(5);

        return dataSource;
    }


}
