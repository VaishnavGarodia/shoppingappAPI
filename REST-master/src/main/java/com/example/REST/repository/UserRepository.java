package com.example.REST.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.REST.model.OrderDetail;
import com.example.REST.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	public User findByEmail(String email);
}
