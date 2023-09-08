package com.ecommerce.app.Service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.app.Entity.Order;
import com.ecommerce.app.External.Client.ProductService;
import com.ecommerce.app.Model.OrderRequest;
import com.ecommerce.app.Repository.OrderRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductService productService;
	
	@Override
	public long PlaceOrder(OrderRequest orderRequest) {
		
		//OrderEntity-> Save the data with Status Order Created
		//ProductService-> BlockProducts(Reduce the Quantity)
		//Payment Service-> Payments -> Sucess-> Complete, Else Cancelled
		log.info("Placing Order Request:{}",orderRequest);
		
		productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());
		
		log.info("Creating Order with status Created");
		
		Order order=Order.builder()
				.amount(orderRequest.getTotalAmount())
				.orderStatus("CREATED")
				.productid(orderRequest.getProductId())
				.orderDate(Instant.now())
				.quantity(orderRequest.getQuantity())
				.build();
		order=orderRepository.save(order);
		log.info("Order Placed sucessfully with order ID:{}",order.getId());
		return order.getId();
	}

}
