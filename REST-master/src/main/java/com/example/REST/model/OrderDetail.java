package com.example.REST.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_detail")
public class OrderDetail {
	
	@Id
	@GeneratedValue
	
	@Column(name="id")
	private int id;
	
	@Column(name="pname")
	private String productName;
	
	
	@Column(name="email")
	private String email;
	
	public String getToken() {
		return token;
	}



	public void setToken(String token) {
		this.token = token;
	}



	@Column(name="orderdate")
	private String orderDate;
	
	
	@Column(name="token")
	private String token;
	
	
	public OrderDetail() {
		
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getProductName() {
		return productName;
	}



	public void setProductName(String productName) {
		this.productName = productName;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getOrderDate() {
		return orderDate;
	}



	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	

	
}
