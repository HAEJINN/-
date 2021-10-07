package com.ecommerce.domain.nft.api;

import com.ecommerce.domain.item.domain.Item;
import com.ecommerce.domain.nft.application.NftService;
import com.ecommerce.domain.nft.domain.NftRequest;
import com.ecommerce.domain.nft.domain.NftResponse;
import com.ecommerce.domain.nft.domain.NftTransferRequest;
import com.ecommerce.domain.user.application.UserService;
import com.ecommerce.domain.wallet.application.WalletService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class NftApi {
    private final NftService nftService;
    private final WalletService walletService;
    private final UserService userService;

    @ApiOperation(value = "Add NFT")
    @PostMapping("/api/v1/nft")
    public ResponseEntity<?> addNft(@RequestBody NftRequest nftRequest) throws Exception {
        System.out.println("NFT 등록 컨트롤러");
        Item item = nftService.createNftToken(nftRequest);
        if(item == null){
            return new ResponseEntity<>("fail",HttpStatus.NO_CONTENT);
        }
        NftResponse nftResponse = new NftResponse(item);
        return ResponseEntity.ok().body(nftResponse);
    }

    @ApiOperation(value = "Smart Contract Deploy")
    @GetMapping("/api/v1/deploy")
    public String deploy() throws Exception {
        return nftService.deployCredential();
    }

    @ApiOperation(value = "Buy NFT")
    @PostMapping("/api/v1/nft/buy")
    public ResponseEntity<?> buyNft(NftTransferRequest nftTransferRequest) throws Exception {
        boolean result = nftService.transferNft(nftTransferRequest);
        if(result) return new ResponseEntity<>("success",HttpStatus.OK);
        else return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
    }
}
