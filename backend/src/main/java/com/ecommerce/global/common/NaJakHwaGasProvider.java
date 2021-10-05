package com.ecommerce.global.common;

import org.springframework.stereotype.Component;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;

@Component
public class NaJakHwaGasProvider implements ContractGasProvider {
    @Override
    public BigInteger getGasPrice(String contractFunc) {
        return BigInteger.valueOf(1L);
    }

    @Override
    public BigInteger getGasPrice() {
        return BigInteger.valueOf(1L);
    }

    @Override
    public BigInteger getGasLimit(String contractFunc) {
        return BigInteger.valueOf(3000000);
    }

    @Override
    public BigInteger getGasLimit() {
        return BigInteger.valueOf(3000000);
    }
}
