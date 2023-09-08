package com.ecommerce.app.External.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("PRODUCT-SERVICE")
public interface ProductService {
	
	@PutMapping("/product/reducequantity/{id}")
	ResponseEntity<Void> reduceQuantity(
			@PathVariable("id") long productid,
			@RequestParam long quantity);

}
