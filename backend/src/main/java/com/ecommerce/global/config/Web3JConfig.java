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
import org.web3j.businesslogin.BusinessLogin;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;

import javax.sql.DataSource;
import java.io.IOException;
import java.math.BigInteger;

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

    @Value("${eth.erc721.contract}")
    private String NFT_CONTRACT_ADDRESS;

    @Bean
    public BusinessLogin businessLogin(){
        Web3j web3j = Web3j.build(new HttpService());
        Credentials cr = Credentials.create("0xa950f8e8a1d275aac181a6bbeb61767db7fc18f0");
        ContractGasProvider contractGasProvider = new ContractGasProvider() {
            @Override
            public BigInteger getGasPrice(String contractFunc) {
                return BigInteger.valueOf(100_000_0L);
            }

            @Override
            public BigInteger getGasPrice() {
                return BigInteger.valueOf(100_000_0L);
            }

            @Override
            public BigInteger getGasLimit(String contractFunc) {
                return BigInteger.valueOf(8000000);
            }

            @Override
            public BigInteger getGasLimit() {
                return BigInteger.valueOf(8000000);
            }
        };

        return BusinessLogin.load("0x"+NFT_CONTRACT_ADDRESS,web3j, cr,contractGasProvider);
    }

}
