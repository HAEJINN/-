package com.ecommerce.domain.purchase.domain;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PurchaseStateTest {

    @Disabled("toString test")
    @Test
    void test() {
        final String purchaseStateWord = PurchaseState.CANCELLED.toString();
        assertThat(purchaseStateWord).isEqualTo("C");
    }

}