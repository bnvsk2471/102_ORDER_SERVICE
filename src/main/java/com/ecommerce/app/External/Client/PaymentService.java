package com.ecommerce.app.External.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecommerce.app.Model.PaymentRequest;

@FeignClient(name="PAYMENT-SERVICE/payment")
public interface PaymentService {
	@PostMapping
	public ResponseEntity<Long> doPayment(
			@RequestBody PaymentRequest paymentRequest);

}
