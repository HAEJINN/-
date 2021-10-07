package com.ecommerce.domain.nft.domain;

import lombok.Data;

@Data
public class NftTransferRequest {
    private String fromAddress;
    private int tokenId;
    private String amount;
    private Long userId;
}
