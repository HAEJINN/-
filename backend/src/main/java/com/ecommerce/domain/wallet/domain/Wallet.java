package com.ecommerce.domain.wallet.domain;

import com.ecommerce.domain.user.domain.User;
import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "wallets")
@Entity
public class Wallet {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wallet_id")
    private Long id;

    @Column(name = "credentials_address")
    private String credentialsAddress;

    @Column(name = "wallet_address")
    private String walletAddress;

    @Column(name = "wallet_balance")
    private BigInteger balance;

    @Column(name = "wallet_cash")
    private int walletCash;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Wallet(final String credentials_address, final String wallet_address,
                  final BigInteger balance, final int walletCash, final User user) {
        this.credentialsAddress = credentials_address;
        this.walletAddress = wallet_address;
        this.balance = balance;
        this.walletCash = walletCash;
        this.user = user;
    }

    public Wallet changeBalance(final BigInteger balance) {
        this.balance = balance;
        return this;
    }

}
