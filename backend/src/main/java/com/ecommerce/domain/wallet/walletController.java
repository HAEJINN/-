package com.ecommerce.domain.wallet;


import com.ecommerce.domain.user.domain.User;
import com.google.api.Http;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.web3j.crypto.CipherException;

import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
public class walletController{
    static Logger logger = LoggerFactory.getLogger(walletController.class);

    private final WalletService walletService;

    //지갑이 없다면 지갑 생성, 자신의mnemonic은 가지고 있기 나중에 잃어버린 지갑 import 시킬때 필요
    // 그리고 address, privatekey반환

    @PostMapping("/login/create/test")
    public ResponseEntity<?> createWallet(@RequestBody User user) throws InvalidAlgorithmParameterException, CipherException, NoSuchAlgorithmException, IOException, NoSuchProviderException {
        Map<String,String> map = walletService.createWallet(user);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }


    //계좌잔액확인, 계좌가 정상적으로 확인되면 값을 출력하고 아니면, 1을 반환
    @GetMapping("/login/balance/test")
    public ResponseEntity<?> getBalance(User user, String address) throws IOException {
        Map<String,BigInteger> map = walletService.getBalance(user, address);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    //id로 주소 확인
    @GetMapping("login/getaddress/test")
    public ResponseEntity<?> getAddress(User user) throws  IOException{
        Map<String, String> map = walletService.getWalletAddressbyuser(user);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }


    //충전 한번하기
//    @PostMapping("login/reqeth/test")
//    public ResponseEntity<?> reqEth(User user, String address) throws IOException {
//
//        return new ResponseEntity<>(map, HttpStatus.OK);
//
//    }


    //지갑주소가져오기
//    @GetMapping("/login/test")
//    public void loadWallet(String password,String fileName) throws CipherException, IOException {
//
//        credentials = WalletUtils.loadCredentials(password,"C:\\Users\\multicampus\\IdeaProjects\\S05P21C201\\backend\\src\\main\\resources\\key\\"+fileName);
////        String filename = WalletUtils.generateNewWalletFile("1234",new File());
//        System.out.println(credentials.getAddress());
//        System.out.println(credentials.getEcKeyPair().getPrivateKey().toString(16));
//
//    }
}
