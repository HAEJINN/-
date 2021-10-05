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

    public static final String walletDirectory = "../wallet/";

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wallet_id")
    private Long id;

    @Column(name = "wallet_address")
    private String address;

    @Column(name = "wallet_file_name")
    private String fileName;

    @Column(name = "wallet_balance")
    private BigInteger balance;

    @Column(name = "wallet_cash")
    private int walletCash;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Wallet(final String address, final String fileName, final BigInteger balance, final int walletCash, final User user) {
        this.address = address;
        this.fileName = fileName;
        this.balance = balance;
        this.walletCash = walletCash;
        this.user = user;
        user.changeWallet(this);
    }

    public Wallet changeBalance(final BigInteger balance) {
        this.balance = balance;
        return this;
    }

}
