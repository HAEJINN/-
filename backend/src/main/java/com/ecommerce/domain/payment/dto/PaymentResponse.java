package com.ecommerce.domain.payment.dto;

import com.ecommerce.domain.payment.domain.Payment;
import com.ecommerce.domain.wallet.domain.Wallet;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentResponse {

    private Long id;
    private String impUid;
    private String merchantUid;
    private BigInteger nowBalance;

    public PaymentResponse(final Payment payment, final Wallet wallet) {
        this.id = payment.getId();
        this.impUid = payment.getImpUid();
        this.merchantUid = payment.getMerchantUid();
        this.nowBalance = wallet.getBalance();
    }

}
