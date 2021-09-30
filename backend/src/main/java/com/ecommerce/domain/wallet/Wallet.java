package com.ecommerce.domain.wallet;

import com.ecommerce.domain.user.domain.User;
import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table
@Entity

public class Wallet {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wallet_id")
    private Long id;

    @Column(name = "wallet_address")
    private String address;

    @Column(name = "wallet_balance")
    private BigInteger balance;

    @Column(name = "wallet_cash")
    private int wallet_cash;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder

    public Wallet(Long id, String address, BigInteger balance, int wallet_cash, User user) {
        this.id = id;
        this.address = address;
        this.balance = balance;
        this.wallet_cash = wallet_cash;
        this.user = user;
    }
}
