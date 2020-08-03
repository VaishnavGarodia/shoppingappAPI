package com.example.REST.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.REST.model.OrderDetail;

public interface OrderRepository extends JpaRepository<OrderDetail, Integer>{
	
	public List<OrderDetail>findByEmail(String email);
	//public OrderDetail findByEmail(String email);

}
