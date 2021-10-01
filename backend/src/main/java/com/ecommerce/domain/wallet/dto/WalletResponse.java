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
    private String walletAddress;
    private String credentialsAddress;
    private int wallet_cash;
    private BigInteger balance;
    private String userName;

    public static final WalletResponse ofWallet(final Wallet wallet) {
        final Long id = wallet.getId();
        final String walletAddress = wallet.getWalletAddress();
        final String credentialsAddress = wallet.getCredentialsAddress();
        final int walletCash = wallet.getWalletCash();
        final BigInteger balance = wallet.getBalance();
        final String userName = wallet.getUser().getName();
        return new WalletResponse(id, walletAddress, credentialsAddress, walletCash, balance, userName);
    }

    private WalletResponse(final Long id, final String walletAddress, final String credentialsAddress,
                          final int wallet_cash, final BigInteger balance, final String userName) {
        this.id = id;
        this.walletAddress = walletAddress;
        this.credentialsAddress = credentialsAddress;
        this.wallet_cash = wallet_cash;
        this.balance = balance;
        this.userName = userName;
    }

}
