package com.ecommerce.domain.nft.domain;

import lombok.Data;

@Data
public class NftRequest {
    private Long userId;
    private String cid;
    private String walletAddress;
    private String name;
    private String description;
    private int price;
}
