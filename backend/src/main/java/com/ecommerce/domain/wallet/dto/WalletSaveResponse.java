package com.ecommerce.domain.wallet.dto;

import com.ecommerce.domain.wallet.domain.Wallet;

import java.math.BigDecimal;


public class WalletSaveResponse {

    private Long id;
    private String user;
    private String address;
    private BigDecimal balance = BigDecimal.valueOf(0);
    private int receivingCount = 0;
    private int cash = 0;

    public static WalletSaveResponse ofWallet(final Wallet wallet) {
        final Long id = wallet.getId();
        final String user = wallet.getUser().getName();
        final String address = wallet.getAddress();
        final BigDecimal balance = wallet.getBalance();
        final int receivingCount = wallet.getReceivingCount();
        final int cash = wallet.getCash();
        return new WalletSaveResponse(id, user, address, balance, receivingCount, cash);
    }

    public WalletSaveResponse(final Long id, final String user, final String address,
                              final BigDecimal balance, final int receivingCount, final int cash) {
        this.id = id;
        this.user = user;
        this.address = address;
        this.balance = balance;
        this.receivingCount = receivingCount;
        this.cash = cash;
    }

}
