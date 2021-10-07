package com.ecommerce.domain.wallet.api;


import com.ecommerce.domain.auth.domain.SessionUser;
import com.ecommerce.domain.payment.dto.PaymentSaveRequest;
import com.ecommerce.domain.wallet.application.WalletService;
import com.ecommerce.domain.wallet.domain.Wallet;
import com.ecommerce.domain.wallet.dto.WalletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
public class walletController {

    //내 local admin 지갑
    private String adminEthAddress = "0x22510CbFA46e5dC4b625BD16f854024f2E6114b5";

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
    public ResponseEntity<WalletResponse> reqEth(@RequestBody final PaymentSaveRequest paymentSaveRequest) throws IOException, ExecutionException, InterruptedException {
        walletService.transactionFunction(paymentSaveRequest);
        final Wallet wallet = walletService.getBalance(paymentSaveRequest);
        final WalletResponse walletResponse = WalletResponse.ofWallet(wallet);
        return ResponseEntity.ok().body(walletResponse);
    }

}
