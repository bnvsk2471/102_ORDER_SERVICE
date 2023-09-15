package com.ecommerce.app.Service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.app.Entity.Order;
import com.ecommerce.app.Exception.CustomException;
import com.ecommerce.app.External.Client.PaymentService;
import com.ecommerce.app.External.Client.ProductService;
import com.ecommerce.app.Model.OrderRequest;
import com.ecommerce.app.Model.OrderResponse;
import com.ecommerce.app.Model.PaymentRequest;
import com.ecommerce.app.Repository.OrderRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductService productService;

	@Autowired
	private PaymentService paymentService;

	@Override
	public long PlaceOrder(OrderRequest orderRequest) {

		// OrderEntity-> Save the data with Status Order Created
		// ProductService-> BlockProducts(Reduce the Quantity)
		// Payment Service-> Payments -> Sucess-> Complete, Else Cancelled
		log.info("Placing Order Request:{}", orderRequest);

		productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());

		log.info("Creating Order with status Created");

		Order order = Order.builder().amount(orderRequest.getTotalAmount()).orderStatus("CREATED")
				.productid(orderRequest.getProductId()).orderDate(Instant.now()).quantity(orderRequest.getQuantity())
				.build();
		order = orderRepository.save(order);

		log.info("Calling payment service to complete the payment");
		PaymentRequest paymentRequest = PaymentRequest.builder().orderId(order.getId())
				.paymentMode(orderRequest.getPaymentMode()).amount(orderRequest.getTotalAmount()).build();
		String OrderStatus = null;
		try {
			paymentService.doPayment(paymentRequest);
			log.info("Payment done sucessfully");
			OrderStatus = "PLACED";
		} catch (Exception e) {
			log.error("Error occured in payment");
			OrderStatus = "PAYMENT FAILED";
		}
		order.setOrderStatus(OrderStatus);
		orderRepository.save(order);
		log.info("Order Placed sucessfully with order ID:{}", order.getId());
		return order.getId();
	}

	@Override
	public OrderResponse getOrderDetails(long orderId) {
		log.info("Get order details for OrderId : {}", orderId);
		Order order=orderRepository.findById(orderId)
				.orElseThrow(()->new CustomException("Order not found for the ID"+orderId, "NOT_FOUND", 404));
		OrderResponse orderResponse=
				OrderResponse.builder()
				.orderId(order.getId())
				.orderStatus(order.getOrderStatus())
				.amount(order.getAmount())
				.orderDate(order.getOrderDate())
				.build();
		return orderResponse;
	}

}
