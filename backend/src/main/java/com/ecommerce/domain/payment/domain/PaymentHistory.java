package com.ecommerce.domain.payment.domain;

import com.ecommerce.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "PaymentLists")
@Entity
public class PaymentHistory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id", updatable = false)
    private Payment payment;

    @Column(name = "payment_lists_amount", updatable = false)
    private BigInteger amount;

    @Builder
    public PaymentHistory(final User user, final Payment payment, final BigInteger amount) {
        this.user = user;
        this.payment = payment;
        this.amount = amount;
    }

    public PaymentHistory changeUser(final User user) {
        this.user = user;
        return this;
    }

    public PaymentHistory changePayment(final Payment payment) {
        this.payment = payment;
        return this;
    }

}

