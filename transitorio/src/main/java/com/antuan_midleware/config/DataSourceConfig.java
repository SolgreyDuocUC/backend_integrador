package com.antuan_midleware.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Primary
    @Bean(name = "legacyDataSource")
    @ConfigurationProperties(prefix = "legacy.datasource")
    public DataSource legacyDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "erpDataSource")
    @ConfigurationProperties(prefix = "erp.datasource")
    public DataSource erpDataSource() {
        return DataSourceBuilder.create().build();
    }
}
