package com.ecommerce.domain.item.domain;

import com.ecommerce.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "Items")
@Entity
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private int price;

    @Column(name = "token_id")
    private BigInteger tokenId;

    private String cid;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Item(final String name, final String description,
                final int price, final BigInteger tokenId, final String cid, final User user) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.tokenId = tokenId;
        this.cid = cid;
        this.user = user;
    }

    public Item updateUser(final User buyer) {
        this.user = buyer;
        return this;
    }

    public void update(final Item updateData) {

    }

}
