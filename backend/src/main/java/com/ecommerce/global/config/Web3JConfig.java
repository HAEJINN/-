package com.ecommerce.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.http.HttpService;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class Web3JConfig {
    @Value("${spring.web3j.client-address}")
    private String NETWORK_URL;

    @Bean
    public Web3j web3j() {
        return Web3j.build(new HttpService(NETWORK_URL));
    }

    @Bean
    public Admin admin() {
        return Admin.build(new HttpService(NETWORK_URL));
    }

}
