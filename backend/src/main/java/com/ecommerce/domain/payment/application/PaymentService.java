package com.ecommerce.domain.payment.application;

import com.ecommerce.domain.payment.domain.Payment;
import com.ecommerce.domain.payment.domain.PaymentHistory;
import com.ecommerce.domain.payment.domain.PaymentHistoryRepository;
import com.ecommerce.domain.payment.domain.PaymentRepository;
import com.ecommerce.domain.payment.dto.PaymentHistoriesResponse;
import com.ecommerce.domain.payment.dto.PaymentSaveRequest;
import com.ecommerce.domain.user.domain.User;
import com.ecommerce.domain.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class PaymentService {

    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentHistoryRepository paymentHistoryRepository;

    @Transactional
    public Payment save(final String email, final PaymentSaveRequest paymentSaveRequest) {
        final User user = userRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new);
        final Payment payment = paymentSaveRequest.toEntity();
        final PaymentHistory paymentHistory = PaymentHistory.builder()
                .user(user)
                .payment(payment)
                .amount(paymentSaveRequest.getAmount()).build();
        payment.addPaymentHistories(paymentHistory);
        return paymentRepository.save(payment);
    }

    public List<PaymentHistoriesResponse> findPaymentHistoriesByUserId(final Long userId) {
        final User user = userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
        final List<PaymentHistory> paymentHistories = paymentHistoryRepository.findByUser(user);
        return paymentHistories.stream()
                .map(PaymentHistoriesResponse::ofPaymentHistory)
                .collect(Collectors.toList());
    }

    public List<PaymentHistoriesResponse> findPaymentHistoriesByMyEmail(final String email) {
        final User user = userRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new);
        final List<PaymentHistory> paymentHistories = paymentHistoryRepository.findByUser(user);
        return paymentHistories.stream()
                .map(PaymentHistoriesResponse::ofPaymentHistory)
                .collect(Collectors.toList());
    }

}

