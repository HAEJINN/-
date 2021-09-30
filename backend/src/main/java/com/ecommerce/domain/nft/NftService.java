package com.ecommerce.domain.nft;

import com.ecommerce.domain.item.domain.Item;
import com.ecommerce.domain.item.domain.ItemRepository;
import com.ecommerce.domain.user.domain.User;
import com.ecommerce.domain.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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
@RequiredArgsConstructor
public class NftService {
    private final static Web3j web3j = Web3j.build(new HttpService());
    @Value("${eth.erc721.contract}")
    private String NFT_CONTRACT_ADDRESS;

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    public Item createNftToken(NftRequest nftRequest) throws Exception {
        System.out.println("nft test start");
        Credentials credentials = WalletUtils.loadCredentials("eth0second", "C:\\Users\\multicampus\\BCSSAFY\\0928\\backend\\src\\main\\resources\\key\\test.wallet");
        Credentials cr = Credentials.create(credentials.getEcKeyPair().getPrivateKey().toString(16));
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

        System.out.println(NFT_CONTRACT_ADDRESS);
        BusinessLogin businessLogin = BusinessLogin.load("0x"+NFT_CONTRACT_ADDRESS,web3j, cr,contractGasProvider);

        TransactionReceipt r = businessLogin.mint(nftRequest.getCID(),nftRequest.getWalletAddress()).send();
        BigInteger tokenId = businessLogin.getCount().send();
        int count = Integer.parseInt(String.valueOf(tokenId)) - 1;
        Tuple3<String, BigInteger, String> c = businessLogin.charactors(BigInteger.valueOf(count)).send();
        System.out.println(c.component1() +" "+c.component2()+" "+c.component3());

        System.out.println(businessLogin.ownerOf(BigInteger.valueOf(0)).send());
        System.out.println("nft test end");
        //아이템에 save ( 이름, 설명, 가격, cid, nft토큰 번호, 유저ID로 찾아서 user) 넣기

        User user = userRepository.findById(nftRequest.getUserId()).orElseThrow(IllegalArgumentException::new);

        Item item = Item.builder()
                .name(nftRequest.getName())
                .description(nftRequest.getDescription())
                .price(nftRequest.getPrice())
                .cid(nftRequest.getCID())
                .token_id(tokenId)
                .user(user)
                .build();
        itemRepository.save(item);
        return item;
    }

    public void deployCredential() throws Exception {
        Credentials credentials = WalletUtils.loadCredentials("eth0second", "C:\\Users\\multicampus\\BCSSAFY\\0928\\backend\\src\\main\\resources\\key\\test.wallet");
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
        System.out.println(businessLogin.getContractAddress());
    }


}
