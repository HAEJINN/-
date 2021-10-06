package com.ecommerce.domain.payment.dto;

import com.ecommerce.domain.payment.domain.Payment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentSaveRequest {

    @JsonProperty("receiver")
    private String receiver;

    @JsonProperty("amount")
    private String amount;

    @JsonProperty("impUid")
    private String impUid;

    @JsonProperty("merchantUid")
    private String merchantUid;


    public PaymentSaveRequest(final String receiver, final String amount, final String impUid, final String merchantUid) {
        this.receiver = receiver;
        this.amount = amount;
        this.impUid = impUid;
        this.merchantUid = merchantUid;
    }

    public Payment toEntity() {
        return Payment.builder()
                .impUid(impUid)
                .merchantUid(merchantUid)
                .build();
    }

}
