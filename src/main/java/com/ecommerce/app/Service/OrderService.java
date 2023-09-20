package com.ecommerce.app.Service;

import java.util.List;

import com.ecommerce.app.Entity.Order;
import com.ecommerce.app.Model.OrderRequest;
import com.ecommerce.app.Model.OrderResponse;

public interface OrderService {

	public long PlaceOrder(OrderRequest orderRequest);

	public OrderResponse getOrderDetails(long orderId);
	
	public List<Order> getAllOrderDetails();

}
