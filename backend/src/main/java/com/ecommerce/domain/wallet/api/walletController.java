package com.ecommerce.domain.wallet.api;


import com.ecommerce.domain.auth.domain.SessionUser;
import com.ecommerce.domain.user.domain.User;
import com.ecommerce.domain.wallet.application.WalletService;
import com.ecommerce.domain.wallet.domain.Wallet;
import com.ecommerce.domain.wallet.dto.WalletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.PersonalListAccounts;
import org.web3j.protocol.admin.methods.response.PersonalUnlockAccount;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
public class walletController{

    //내 local admin 지갑
    private String adminEthAddress = "0x2bd661bad97160c81eb0704ae29cb97bcbec6f8a";

    private final WalletService walletService;
    private Admin admin;
    List<String> addressList;

    @PostMapping("/wallet/create")
    public ResponseEntity<?> createWallet(@RequestBody User user) throws Exception {
        final Wallet wallet = walletService.createWallet(user);
        return ResponseEntity.ok().body(WalletResponse.ofWallet(wallet));
    }

    @GetMapping("/wallet/balance")
    public ResponseEntity<?> getBalance(final String address) throws Exception {
        final Wallet wallet = walletService.getBalance(address);
        return ResponseEntity.ok().body(WalletResponse.ofWallet(wallet));

    }

    @GetMapping("/wallet/getaddress")
    public ResponseEntity<?> getAddress(HttpSession httpSession) {
        final SessionUser user = (SessionUser) httpSession.getAttribute("user");
        final Wallet wallet = walletService.getWalletAddressByUser(user.getEmail());
        return ResponseEntity.ok().body(WalletResponse.ofWallet(wallet));
    }

    //충전 한번하기
    @PostMapping("/wallet/sendeth")
    public boolean reqEth( String receiver, String amount) throws IOException {
        final Web3j web3j = Web3j.build(new HttpService());
        BigInteger charge = new BigInteger(amount);

        List<Type> inputParmeters = new ArrayList<>();
        inputParmeters.add(new Address(adminEthAddress));
        inputParmeters.add(new Address(receiver));
        inputParmeters.add(new Address(charge));

        // amount 도 같이 보내준 이유가 list에서 charge만 뽑는 법을 모르겠슴둥..
        Transaction thash = walletService.transactionFunction("reqEth", inputParmeters, Collections.emptyList(), amount);
        web3j.ethSendTransaction(thash);

        return true;
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
