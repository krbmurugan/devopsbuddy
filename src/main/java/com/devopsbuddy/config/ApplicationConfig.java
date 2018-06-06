package com.devopsbuddy.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages="com.devopsbuddy.backend.persistence.repositoires")
@EntityScan(basePackages="com.devopsbuddy.backend.persistence.domain")
@EnableTransactionManagement
@PropertySource("file:///${user.home}/config/application-common.properties")
public class ApplicationConfig {

}
