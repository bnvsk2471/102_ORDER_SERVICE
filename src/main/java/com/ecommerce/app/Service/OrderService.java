package com.ecommerce.app.Service;

import com.ecommerce.app.Model.OrderRequest;

public interface OrderService {

	long PlaceOrder(OrderRequest orderRequest);

}
