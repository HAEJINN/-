package com.ecommerce.domain.nft.domain;

import lombok.Data;

@Data
public class NftTransferRequest {
    private String fromAddress;
    private String toAddress;
    private int tokenId;
}
