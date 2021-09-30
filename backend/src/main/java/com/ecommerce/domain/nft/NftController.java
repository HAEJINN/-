package com.ecommerce.domain.nft;

import com.ecommerce.domain.item.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class NftController {
    private final NftService nftService;

    @PostMapping("/api/v1/nft")
    public ResponseEntity<?> addNft(NftRequest nftRequest) throws Exception {
        Item item = nftService.createNftToken(nftRequest);
        return new ResponseEntity<Item>(item, HttpStatus.OK);
    }

    @GetMapping("/api/v1/deploy")
    public void deploy() throws Exception {
        nftService.deployCredential();
    }
}
