package com.ecommerce.domain.wallet.domain;

import com.ecommerce.domain.user.domain.User;
import com.ecommerce.domain.wallet.domain.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByUser(User user);
    Optional<Wallet> findByCredentials_address(String address);
}
