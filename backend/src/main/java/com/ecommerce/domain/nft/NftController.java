package com.ecommerce.domain.nft;

import com.ecommerce.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.crypto.Wallet;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class NftController {
    private final NftService nftService;

    @PostMapping("/api/v1/nft")
    public ResponseEntity<?> addNft(User user, Wallet wallet,String cid) throws Exception {
        nftService.createNftToken();
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }
}
