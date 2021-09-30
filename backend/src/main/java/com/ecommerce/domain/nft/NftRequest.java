package com.ecommerce.domain.nft;

import lombok.Data;

@Data
public class NftRequest {
    private Long userId;
    private String CID;
    private String walletAddress;
    private String name;
    private String description;
    private int price;
}
