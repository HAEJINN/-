package com.ecommerce;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.web3j.EVMTest;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.simpleauction.SimpleAuction;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;
import java.util.List;

@EVMTest
public class AuctionTest {

    @DisplayName("Auction 클래스 맛뵈기 ")
    @Test
    void test() throws Exception {
        final Web3j web3j = Web3j.build(new HttpService());
        final EthGetBalance send1 = web3j.ethGetBalance("0xF771a361cA41BB1E1f1b092B89b1982f98cb7Dea", DefaultBlockParameterName.LATEST).send();

        System.out.println(send1.getBalance());
        final EthAccounts send2 = web3j.ethAccounts().send();
        final List<String> accounts = send2.getAccounts();
        for (String account : accounts) {
            System.out.println("account = " + account);
        }
        final Credentials rladnwo1 = WalletUtils.loadCredentials("rladnwo1", "C:\\Users\\multicampus\\Desktop\\UTC--2021-09-01T06-38-46.036396795Z--f771a361ca41bb1e1f1b092b89b1982f98cb7dea");
        SimpleAuction simpleAuction = SimpleAuction.deploy(web3j, new RawTransactionManager(web3j, rladnwo1),
                new StaticGasProvider(BigInteger.valueOf(30000L), BigInteger.valueOf(100_000_0L)), BigInteger.ONE, "1632726500").send();
//        final TransactionReceipt send3 = simpleAuction.bid(BigInteger.valueOf(1000)).send();
//        System.out.println("send.toString() = " + send3.toString());
        final TransactionReceipt send = simpleAuction.auctionEnd().send();
        System.out.println("send.toString() = " + send.toString());
    }
}
