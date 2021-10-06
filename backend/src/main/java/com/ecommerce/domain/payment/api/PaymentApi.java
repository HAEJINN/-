package com.ecommerce.domain.payment.api;

import com.ecommerce.domain.dto.PaymentResponse;
import com.ecommerce.domain.payment.application.PaymentService;
import com.ecommerce.domain.payment.domain.Payment;
import com.ecommerce.domain.payment.dto.PaymentHistoriesResponse;
import com.ecommerce.domain.payment.dto.PaymentSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PaymentApi {

    private final PaymentService paymentService;

    @PostMapping("/api/v1/payments")
    public ResponseEntity<PaymentResponse> save(@AuthenticationPrincipal final String email, final PaymentSaveRequest paymentSaveRequest) {
        final Payment payment = paymentService.save(email, paymentSaveRequest);
        final PaymentResponse paymentResponse = new PaymentResponse(payment);
        return ResponseEntity.ok().body(paymentResponse);
    }

    @GetMapping("/api/v1/payments/{userId}")
    public ResponseEntity<List<PaymentHistoriesResponse>> findPaymentHistoriesByUserId(@PathVariable final Long userId) {
        final List<PaymentHistoriesResponse> paymentHistories = paymentService.findPaymentHistoriesByUserId(userId);
        return ResponseEntity.ok().body(paymentHistories);
    }

    @GetMapping("/api/v1/payments/me")
    public ResponseEntity<List<PaymentHistoriesResponse>> findPaymentHistoriesByUserId(@AuthenticationPrincipal final String email) {
        final List<PaymentHistoriesResponse> paymentHistories = paymentService.findPaymentHistoriesByMyEmail(email);
        return ResponseEntity.ok().body(paymentHistories);
    }

}
