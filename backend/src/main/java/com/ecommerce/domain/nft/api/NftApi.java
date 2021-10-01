package com.ecommerce.domain.nft.api;

import com.ecommerce.domain.item.domain.Item;
import com.ecommerce.domain.nft.domain.NftRequest;
import com.ecommerce.domain.nft.application.NftService;
import com.ecommerce.domain.nft.domain.NftTransferRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class NftApi {
    private final NftService nftService;

    @PostMapping("/api/v1/nft")
    public ResponseEntity<?> addNft(NftRequest nftRequest) throws Exception {
        Item item = nftService.createNftToken(nftRequest);
        return new ResponseEntity<Item>(item, HttpStatus.OK);
    }

    @GetMapping("/api/v1/deploy")
    public String deploy() throws Exception {
        return nftService.deployCredential();
    }

    @PostMapping("/api/v1/nft/buy")
    public ResponseEntity<?> buyNft(NftTransferRequest nftTransferRequest) throws Exception {
        boolean result = nftService.transferNft(nftTransferRequest.getFromAddress(), nftTransferRequest.getToAddress(), nftTransferRequest.getTokenId());
        if(result) return new ResponseEntity<>("success",HttpStatus.OK);
        else return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
    }
}
