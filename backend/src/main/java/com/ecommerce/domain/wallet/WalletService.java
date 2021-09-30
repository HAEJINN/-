package com.ecommerce.domain.wallet;

import com.ecommerce.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Bip39Wallet;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.http.HttpService;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;
    private Web3j web3j;

    public Map<String, String> createWallet(User user) throws CipherException, IOException {
//        if(!WALLET_DIR.exists()){
//            WALLET_DIR.mkdir();
//        }
//        String filename = WalletUtils.generateNewWalletFile(WALLET_PASS, WALLET_DIR);
        //path 지정 하는법, 배포했을때 경로 지정은 어떻게 할지?
        Bip39Wallet bip39Wallet = WalletUtils.generateBip39Wallet(user.getPassword(), new File("C:\\Users\\multicampus\\IdeaProjects\\S05P21C201\\backend\\src\\main\\resources\\key"));
        String fileName = bip39Wallet.getFilename();
//            String mnemonic = bip39Wallet.getMnemonic();
        return getWalletAddress(user, fileName);
//        System.out.println("fileName : "+fileName);
//        System.out.println("mnemonic임당 :" + mnemonic);
//        System.out.println("지갑생성완료");
    }



    public Map<String, String> getWalletAddress(User user, String fileName) throws CipherException, IOException {
        Credentials credentials = WalletUtils.loadCredentials(user.getPassword(), "C:\\Users\\multicampus\\IdeaProjects\\S05P21C201\\backend\\src\\main\\resources\\key\\" + fileName);
//        String filename = WalletUtils.generateNewWalletFile("1234",new File());
        System.out.println(credentials.getAddress());
        System.out.println(credentials.getEcKeyPair().getPrivateKey().toString(16));
        Map<String, String> map = new HashMap<>();
        map.put("WalletAddress", credentials.getAddress());
        map.put("PrivateKey", credentials.getEcKeyPair().getPrivateKey().toString(16));

        Wallet wallet = Wallet.builder()
                .address(credentials.getAddress())
            .user(user)
                .build();

        walletRepository.save(wallet);
        return map;
}

    public Map<String, String> getWalletAddressbyuser(User user){

        Optional<Wallet> findWalletAddress =  walletRepository.findById(user.getId());
        Map<String, String> map = new HashMap<>();
        if(findWalletAddress.isPresent()){
          map.put("WalletAddress", findWalletAddress.toString());
        }

        return map;
    }

    public Map<String, BigInteger> getBalance(User user, String address) throws IOException {

        web3j = Web3j.build(new HttpService());
//      DefaultBlockParameter defaultBlockParameter = new DefaultBlockParameterNumber(web3j.ethBlockNumber().send().getBlockNumber());
        EthGetBalance ethGetBalance = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send();
        Map<String, BigInteger> map = new HashMap<>();
        map.put("Balance", ethGetBalance.getBalance());

        Optional<Wallet> findWallet = walletRepository.findByUser(user);
        if(findWallet.isPresent()){
            Wallet wallet = Wallet.builder()
                    .id(findWallet.get().getId())
                    .user(findWallet.get().getUser())
                    .address(findWallet.get().getAddress())
                    .balance(ethGetBalance.getBalance())
                    .build();

            walletRepository.save(wallet);
        }

        return map;
    }
    //ToDo 트랜잭션 처리방법, 계정을 만들고 1회 한해서 이더리움을 받을 수 있도록 하기
    // user 테이블에 1회 충전이 되었는지 속성 필요
    // 1회 충전을 눌렀을 때 -> user테이블에서 검사하고 user.charge -> boolean 값으로 만들고 만약 fasle이면
    // 충전 가능

}
