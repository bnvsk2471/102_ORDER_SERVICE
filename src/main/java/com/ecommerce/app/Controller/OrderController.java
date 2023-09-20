package com.ecommerce.app.Controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.app.Entity.Order;
import com.ecommerce.app.Model.OrderRequest;
import com.ecommerce.app.Model.OrderResponse;
import com.ecommerce.app.PDF.PDFGenerater;
import com.ecommerce.app.Service.OrderService;
import com.lowagie.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;
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
	
	
	@GetMapping("/{orderId}")
	public ResponseEntity<OrderResponse> getOrderDetails(
			@PathVariable long orderId
			){
		OrderResponse orderResponse= orderService.getOrderDetails(orderId);
		return new ResponseEntity<>(orderResponse,HttpStatus.OK);
	}
	
	@GetMapping("/pdf/students")
	public void generatePdf(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
		String currentDateTime = dateFormat.format(new Date());
		String headerkey = "Content-Disposition";
		String headervalue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
		response.setHeader(headerkey, headervalue);
		
		List<Order> orderList=orderService.getAllOrderDetails();
		
		PDFGenerater generator = new PDFGenerater();
		generator.setOrderList(orderList);
		generator.generate(response);
	}

}
