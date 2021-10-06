package com.ecommerce.domain.payment.dto;

import com.ecommerce.domain.payment.domain.Payment;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentSaveRequest {

    private String receiver;
    private String impUid;
    private String merchantUid;
    private BigInteger amount;

    public PaymentSaveRequest(final String receiver, final String impUid, final String merchantUid, final BigInteger amount) {
        this.receiver = receiver;
        this.impUid = impUid;
        this.merchantUid = merchantUid;
        this.amount = amount;
    }

    public Payment toEntity() {
        return Payment.builder()
                .impUid(impUid)
                .merchantUid(merchantUid)
                .build();
    }

}
