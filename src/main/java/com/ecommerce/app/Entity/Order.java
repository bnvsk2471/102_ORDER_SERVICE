package com.ecommerce.app.Entity;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Order_DETAILS")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;
	
	@Column(name = "PRODUCT_ID")
	private long productid;
	
	@Column(name = "QUANTITY")
	private long quantity;
	
	@Column(name = "ORDER_DATE")
	private Instant orderDate;
	
	@Column(name = "ORDER_STATUS")
	private String orderStatus;
	
	@Column(name = "AMOUNT")
	private long amount;

}
