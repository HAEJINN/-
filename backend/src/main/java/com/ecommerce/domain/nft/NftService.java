package com.ecommerce.domain.nft;

import org.springframework.stereotype.Service;
import org.web3j.businesslogin.BusinessLogin;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;

@Service
public class NftService {
    private final static Web3j web3j = Web3j.build(new HttpService());

    public String createNftToken() throws Exception {
        System.out.println("nft test start");
        Credentials credentials = WalletUtils.loadCredentials("eth0second", "C:\\Users\\multicampus\\BCSSAFY\\0928\\backend\\src\\main\\resources\\key\\test.wallet");
        ContractGasProvider contractGasProvider1 = new DefaultGasProvider();
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
        BusinessLogin businessLogin = BusinessLogin.deploy(web3j, credentials, contractGasProvider).send();

        TransactionReceipt r = businessLogin.mint("ipfs://QmRzzdkBvkRa2Bv7ZcgJMFyQjmC6vnP3CfCeGLDC2xjveF","0x1a30de2e6deecec424b68f601ece2510194ad7b2").send();
        Tuple3<String, BigInteger, String> c = businessLogin.charactors(BigInteger.valueOf(0)).send();
        System.out.println(c.component1() +" "+c.component2()+" "+c.component3());
        System.out.println(businessLogin.getCount().send());
        System.out.println(businessLogin.ownerOf(BigInteger.valueOf(0)).send());
        System.out.println("nft test end");
        return "success";
    }


}
