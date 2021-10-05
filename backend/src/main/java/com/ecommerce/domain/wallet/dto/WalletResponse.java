package com.ecommerce.domain.wallet.dto;

import com.ecommerce.domain.wallet.domain.Wallet;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WalletResponse {

    private Long id;
    private String address;
    private String fileName;
    private int wallet_cash;
    private BigInteger balance;
    private String userName;

    public static final WalletResponse ofWallet(final Wallet wallet) {
        final Long id = wallet.getId();
        final String address = wallet.getAddress();
        final String fileName = wallet.getFileName();
        final int walletCash = wallet.getWalletCash();
        final BigInteger balance = wallet.getBalance();
        final String userName = wallet.getUser().getName();
        return new WalletResponse(id, address, fileName, walletCash, balance, userName);
    }

    private WalletResponse(final Long id, final String address, final String fileName,
                          final int wallet_cash, final BigInteger balance, final String userName) {
        this.id = id;
        this.address = address;
        this.fileName = fileName;
        this.wallet_cash = wallet_cash;
        this.balance = balance;
        this.userName = userName;
    }

}
