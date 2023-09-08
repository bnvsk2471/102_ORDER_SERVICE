package com.ecommerce.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.app.Entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
