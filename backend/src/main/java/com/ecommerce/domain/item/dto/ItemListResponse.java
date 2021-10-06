package com.ecommerce.domain.item.dto;

import com.ecommerce.domain.follow.domain.Follow;
import com.ecommerce.domain.follow.dto.FollowerListResponse;
import com.ecommerce.domain.item.domain.Item;
import com.ecommerce.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.math.BigInteger;
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemListResponse {
    private Long id;
    private String name;
    private String description;
    private int price;
    private String author;
    private BigInteger tokenId;
    private String cid;
    private Long userId;

    public static ItemListResponse ofItem(final Item item) {
        return new ItemListResponse(item.getId(),item.getName(), item.getDescription(), item.getPrice(), item.getAuthor(), item.getTokenId(),item.getCid(),item.getUser().getId());
    }

    public ItemListResponse(Long id, String name, String description, int price, String author, BigInteger tokenId, String cid, Long userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.author = author;
        this.tokenId = tokenId;
        this.cid = cid;
        this.userId = userId;
    }
}
