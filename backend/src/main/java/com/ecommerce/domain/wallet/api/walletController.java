package com.ecommerce.domain.wallet.api;


import com.ecommerce.domain.user.domain.User;
import com.ecommerce.domain.wallet.application.WalletService;
import com.ecommerce.domain.wallet.domain.Wallet;
import com.ecommerce.domain.wallet.dto.WalletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class walletController{

    private final WalletService walletService;

    @PostMapping("/login/create/test")
    public ResponseEntity<?> createWallet(@RequestBody User user) throws Exception {
        final Wallet wallet = walletService.createWallet(user);
        return ResponseEntity.ok().body(WalletResponse.ofWallet(wallet));
    }

    @GetMapping("/login/balance/test")
    public ResponseEntity<?> getBalance(final String address) throws Exception {
        final Wallet wallet = walletService.getBalance(address);
        return ResponseEntity.ok().body(WalletResponse.ofWallet(wallet));

    }

    @GetMapping("login/getaddress/test")
    public ResponseEntity<?> getAddress(final String email) {
        final Wallet wallet = walletService.getWalletAddressByUser(email);
        return ResponseEntity.ok().body(WalletResponse.ofWallet(wallet));
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
