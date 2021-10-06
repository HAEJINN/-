package com.ecommerce.domain.nft.domain;

import com.ecommerce.domain.item.domain.Item;
import com.ecommerce.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
@Getter
public class NftResponse {
    private Long id;
    private String name;
    private String description;
    private int price;
    private BigInteger tokenId;
    private String cid;
    private Long userId;

    public NftResponse(final Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.description = item.getDescription();
        this.price = item.getPrice();
        this.tokenId = item.getTokenId();
        this.cid = item.getCid();
        this.userId = item.getUser().getId();
    }
}
