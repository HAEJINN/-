package com.ecommerce.domain.wallet.domain;

import com.ecommerce.domain.user.domain.User;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Sub PJT Ⅱ
 */
@Getter
@Table(name = "wallets")
@Entity
public class Wallet {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String address;
    private BigDecimal balance = BigDecimal.valueOf(0);
    private int receivingCount = 0;
    private int cash = 0;

    public boolean canRequestEth() {
        return receivingCount < 10;
    }

}
