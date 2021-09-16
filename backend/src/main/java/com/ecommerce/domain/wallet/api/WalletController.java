package com.ecommerce.domain.wallet.api;

import com.ecommerce.domain.wallet.application.WalletService;
import com.ecommerce.domain.wallet.domain.Wallet;
import com.ecommerce.domain.wallet.dto.WalletSaveResponse;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
public class WalletController {
    public final Logger logger = LoggerFactory.getLogger(getClass());

    private final WalletService walletService;

    public WalletController(final WalletService walletService) {
        this.walletService = walletService;
    }

    /**
     * TODO Sub PJT Ⅱ 과제 1
     * 지갑 등록
     *
     * @param wallet
     */
    @ApiOperation("Register wallet of user")
    @PostMapping("/wallets")
    public ResponseEntity<WalletSaveResponse> save(@Valid @RequestBody final Wallet wallet) {
        final Wallet savedWallet = walletService.save(wallet);
        final WalletSaveResponse walletSaveResponse = WalletSaveResponse.ofWallet(savedWallet);
        return ResponseEntity.ok().body(walletSaveResponse);
    }

    /**
     * TODO Sub PJT Ⅱ 과제 1
     * 지갑 조회 by address
     *
     * @param address 지갑 주소
     */
    @ApiOperation("Fetch wallet by address")
    @GetMapping("/wallets/{address}")
    public Wallet get(@PathVariable String address) {
        return null;
    }

    /**
     * TODO Sub PJT Ⅱ 과제 1
     * 지갑 조회 by user's id
     *수정
     * @param userId 사용자 id
     */
    @ApiOperation(value = "Fetch wallet of user")
    @RequestMapping(value = "/wallets/of/{userId}", method = RequestMethod.GET)
    public Wallet getByUser(@PathVariable long userId) {
        final Wallet wallet = walletService.findByUserId(userId);
        return wallet;
    }

    /**
     * TODO Sub PJT Ⅱ 과제 1
     * 이더 충전 요청
     *
     * @param walletAddress 지갑 주소
     */
    @ApiOperation("Request ether")
    @PutMapping("/wallets/{walletAddress}")
    public Wallet requestEth(@PathVariable final String walletAddress) { // 테스트 가능하도록 일정 개수의 코인을 충전해준다.
        final Wallet wallet = walletService.requestEth(walletAddress);
        return wallet;
    }

}
