package com.ecommerce.domain.payment.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payment_imp_uid", updatable = false)
    private String impUid;

    @Column(name = "payment_merchant_uid", updatable = false)
    private String merchantUid;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PaymentHistory> paymentHistories = new ArrayList<>();

    @Builder
    public Payment(final String impUid, final String merchantUid) {
        this.impUid = impUid;
        this.merchantUid = merchantUid;
    }

    public Payment changeImpUid(final String impUid) {
        validateNull(impUid);
        this.impUid = impUid;
        return this;
    }

    public Payment changeMerchantUid(final String merchantUid) {
        validateNull(merchantUid);
        this.merchantUid = merchantUid;
        return this;
    }

    public void addPaymentHistories(final PaymentHistory paymentHistory) {
        paymentHistories.add(paymentHistory);
        paymentHistory.changePayment(this);
    }

    private void validateNull(final String merchantUid) {
        if (Strings.isEmpty(merchantUid)) {
            throw new IllegalArgumentException();
        }
    }


}
