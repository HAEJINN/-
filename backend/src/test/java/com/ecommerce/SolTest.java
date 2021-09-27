package com.ecommerce;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.web3j.EVMTest;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

@EVMTest
public class SolTest {

    @DisplayName("getEthClientVersionSync")
    @Test
    public void getEthClientVersionSync() throws Exception
    {
        Web3j web3j = Web3j.build(new HttpService("http://3.36.50.29:8545"));
        Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
        System.out.println(">>> web3ClientVersion = " + web3ClientVersion.getWeb3ClientVersion());
    }

    @DisplayName("getEthClientVersionASync")
    @Test
    public void getEthClientVersionASync() throws Exception
    {
        Web3j web3j = Web3j.build(new HttpService("http://3.36.50.29:8545"));
        Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().sendAsync().get();
        System.out.println(">>> web3ClientVersion = " + web3ClientVersion.getWeb3ClientVersion());
    }

    @DisplayName("getEthClientVersionRx")
    @Test
    public void getEthClientVersionRx() throws Exception
    {
        Web3j web3j = Web3j.build(new HttpService("http://3.36.50.29:8545"));
        web3j.web3ClientVersion().flowable().subscribe(x -> {
            System.out.println(">>> web3ClientVersion = " + x.getWeb3ClientVersion());
        });

        Thread.sleep(5000);
    }

}
