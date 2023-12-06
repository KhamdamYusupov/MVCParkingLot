package com.example.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@ComponentScan(basePackages = "com.example")
public class DatasourceConfig {
    private final Environment environment;

    @Autowired
    public DatasourceConfig(Environment environment) {
        this.environment = environment;
    }
    @Bean
    DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/cars");
        dataSource.setUsername("postgres");
        dataSource.setPassword("Pshtqapipi0209");
        dataSource.setDriverClassName("org.postgresql.Driver");
        return dataSource;
    }
}
