package com.ecommerce.app.Service;

import com.ecommerce.app.Model.OrderRequest;
import com.ecommerce.app.Model.OrderResponse;

public interface OrderService {

	public long PlaceOrder(OrderRequest orderRequest);

	public OrderResponse getOrderDetails(long orderId);

}
