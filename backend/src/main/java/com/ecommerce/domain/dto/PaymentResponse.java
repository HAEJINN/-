package com.ecommerce.domain.dto;

import com.ecommerce.domain.payment.domain.Payment;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentResponse {

    private Long id;
    private String impUid;
    private String merchantUid;

    public PaymentResponse(final Payment payment) {
        this.id = payment.getId();
        this.impUid = payment.getImpUid();
        this.merchantUid = payment.getMerchantUid();
    }

}
