package com.ecommerce.app.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {
	private long orderId;
	private long amount;
	private String referanceNumber;
	private PaymentMode paymentMode;
}
