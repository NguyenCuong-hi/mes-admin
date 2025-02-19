package com.example.mesadmin.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;

@Configuration
@EnableCaching
@ComponentScan(basePackages = {
        // Core
        "com.nvc.core.service",
        "com.nvc.core.entity", "com.nvc.core.repository",
        "com.nvc.core.service.impl", "com.nvc.core.rest",
        "com.example.mesadmin",
        // Security
        "com.nvc.entity.security"})
@EntityScan(basePackages = {"com.nvc.core.entity", "com.example.mesadmin.entity"})
@EnableJpaRepositories(basePackages = { "com.nvc.core.repository", "com.example.mesadmin.repository"})

public class ConfigLoadingBean extends CachingConfigurerSupport {

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    @Override
    public CacheManager cacheManager() {
        // configure and return an implementation of Spring's CacheManager SPI
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("default")));

        return cacheManager;
    }
}
