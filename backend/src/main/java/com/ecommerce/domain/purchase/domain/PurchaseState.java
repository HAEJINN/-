package com.ecommerce.domain.purchase.domain;

/**
 * Sub PJT Ⅲ
 * 상품 구매 상태 표시를 위한 States
 */
public enum PurchaseState {
    INITIAL, PAID, SENT, CONFIRMED, CANCELLED ;

    @Override
    public String toString() {
        return name().substring(0, 1).toUpperCase();
    }

}
