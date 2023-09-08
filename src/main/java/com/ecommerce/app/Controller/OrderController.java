package com.ecommerce.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.app.Model.OrderRequest;
import com.ecommerce.app.Service.OrderService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/order")
@Log4j2
public class OrderController {
	
	@Autowired	
	private OrderService orderService;
	
	@PostMapping("/placeorder")
	public ResponseEntity<Long> PlaceOrder(
			@RequestBody OrderRequest orderRequest ){
		long orderId=orderService.PlaceOrder(orderRequest);
		log.info("Order ID :{}",orderId);
		return new ResponseEntity<Long>(orderId,HttpStatus.CREATED);
	}

}
