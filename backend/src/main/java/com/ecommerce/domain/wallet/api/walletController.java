package com.ecommerce.domain.wallet.api;


import com.ecommerce.domain.auth.domain.SessionUser;
import com.ecommerce.domain.wallet.application.WalletService;
import com.ecommerce.domain.wallet.domain.Wallet;
import com.ecommerce.domain.wallet.dto.WalletRequest;
import com.ecommerce.domain.wallet.dto.WalletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Type;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.http.HttpService;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class walletController{

    //내 local admin 지갑
    private String adminEthAddress = "0xa950f8e8a1d275aac181a6bbeb61767db7fc18f0";

    private final WalletService walletService;

    @PostMapping("/api/v1/wallet/create")
    public ResponseEntity<?> createWallet(HttpSession httpSession) throws Exception {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        final Wallet wallet = walletService.createWallet(user.getEmail());
        return ResponseEntity.ok().body(WalletResponse.ofWallet(wallet));
    }

    @GetMapping("/api/v1/wallet/balance")
    public ResponseEntity<?> getBalance(final String address) throws Exception {
        final Wallet wallet = walletService.getBalance(address);
        return ResponseEntity.ok().body(WalletResponse.ofWallet(wallet));
    }

    @GetMapping("/api/v1/wallet/getaddress")
    public ResponseEntity<?> getAddress(@AuthenticationPrincipal final String email) {
        final Wallet wallet = walletService.getWalletAddressByUser(email);
        return ResponseEntity.ok().body(WalletResponse.ofWallet(wallet));
    }

    //충전 한번하기
    @PostMapping("/api/v1/wallet/sendeth")
    public ResponseEntity<?> reqEth(@RequestBody WalletRequest walletRequest) throws IOException {
        final Web3j web3j = Web3j.build(new HttpService());
        String amount = "100";
        BigInteger charge = new BigInteger(amount);

        List<Type> inputParmeters = new ArrayList<>();
        inputParmeters.add(new Address(adminEthAddress));
        inputParmeters.add(new Address(walletRequest.getReceiver()));
        inputParmeters.add(new Address(charge));

        System.out.println("나옴1");

        // amount 도 같이 보내준 이유가 list에서 charge만 뽑는 법을 모르겠슴둥..
        Transaction thash = walletService.transactionFunction("reqEth", inputParmeters, Collections.emptyList(), amount);
        web3j.ethSendTransaction(thash);

        final Wallet wallet = walletService.getBalance(walletRequest.getReceiver());

        return ResponseEntity.ok().body(WalletResponse.ofWallet(wallet));
    }


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
