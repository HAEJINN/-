package com.ecommerce.domain.payment.domain;

import com.ecommerce.domain.user.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory, Long> {

    @EntityGraph(attributePaths = {"payment", "user"})
    List<PaymentHistory> findByUser(final User user);

}
