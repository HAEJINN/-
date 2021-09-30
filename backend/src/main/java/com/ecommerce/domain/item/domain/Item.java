package com.ecommerce.domain.item.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private String token_address;
    private String cid;

    @Builder
    public Item(final String name, final String description,
                final int price, final String token_address, final String cid) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.token_address = token_address;
        this.cid = cid;
    }

}
