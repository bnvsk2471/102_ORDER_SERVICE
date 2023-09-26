package com.ecommerce.app.Service;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.ecommerce.app.External.Client.PaymentService;
import com.ecommerce.app.External.Client.ProductService;
import com.ecommerce.app.Repository.OrderRepository;

@SpringBootTest
public class OrderServiceImplTest {
	
	@Mock
	private OrderRepository orderRepository;

	@Mock
	private ProductService productService;

	@Mock
	private PaymentService paymentService;
	
	
	@InjectMocks
	OrderService orderService=new OrderServiceImpl();
	//This is the class where all the mocks are injected
	

}
