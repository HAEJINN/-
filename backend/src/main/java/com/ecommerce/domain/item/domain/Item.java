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
    private BigInteger token_id;
    private String cid;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Item(final String name, final String description,
                final int price, final BigInteger token_id, final String cid, final User user) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.token_id = token_id;
        this.cid = cid;
        this.user = user;
    }

}
