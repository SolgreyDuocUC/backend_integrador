package com.antuan_midleware.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "erpEntityManagerFactory", transactionManagerRef = "erpTransactionManager", basePackages = {
        "com.antuan_midleware.target.repository"
})
public class ErpDbConfig {

    @Bean(name = "erpEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean erpEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("erpDataSource") DataSource dataSource) {

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "none");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

        return builder
                .dataSource(dataSource)
                .packages("com.antuan_midleware.target.model")
                .persistenceUnit("erp")
                .properties(properties)
                .build();
    }

    @Bean(name = "erpTransactionManager")
    public PlatformTransactionManager erpTransactionManager(
            @Qualifier("erpEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
