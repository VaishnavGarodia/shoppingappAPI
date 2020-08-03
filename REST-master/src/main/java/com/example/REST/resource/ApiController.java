package com.example.REST.resource;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.REST.model.OrderDetail;
import com.example.REST.model.User;
import com.example.REST.repository.OrderRepository;
import com.example.REST.repository.UserRepository;

@RestController
public class ApiController {
	
	@Autowired
	UserRepository userRepository;
	

	@Autowired
	OrderRepository orderRepository;
	
	HashMap <String, String> tokenMap = new HashMap<String, String>();
	
	
	@PostMapping("/addProduct")
	public ResponseEntity<String> add(@RequestBody OrderDetail order) {
		
		 if(order.getToken()!=null && tokenMap.get(order.getEmail())!=null && tokenMap.get(order.getEmail()).equals(order.getToken())) {
			 
			 DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
				Calendar calobj = Calendar.getInstance();
				System.out.println(df.format(calobj.getTime()));
				order.setOrderDate(df.format(calobj.getTime()));
			
				orderRepository.save(order);
				return new ResponseEntity<String>("success",HttpStatus.OK);
		 }else {
			 return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
		 }
		
	}
	
	
	@PostMapping("/addUser")
	public ResponseEntity<String> add(@RequestBody User user) {
		String token = "";
		
		// Hashing the password ::
		String hashCode = user.getPassword().hashCode() +"";
		user.setPassword(hashCode);
		
		User u = userRepository.save(user);
		  if(u !=null) {
	        	
	        	token = "oocnxm" + Math.random() + "dasd";
	        	tokenMap.put(user.getEmail(), token);
	        	
	        }
	        return new ResponseEntity<String>(token, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/viewOrder/{emailId}/token/{token}")
	public ResponseEntity<List<OrderDetail>> viewAll(@PathVariable String emailId, @PathVariable String token){
		
		  try {
			  
			  if(tokenMap.get(emailId).equals(token)) {
				  System.out.println("Emai ::::::::::"+ emailId);
				  List<OrderDetail> orderList = orderRepository.findByEmail(emailId);
			        return new ResponseEntity<List<OrderDetail>>(orderList, HttpStatus.OK);
			  } else {
				  return new ResponseEntity<List<OrderDetail>>(HttpStatus.FORBIDDEN);
			  }
			 
		    } catch (NoSuchElementException e) {
		        return new ResponseEntity<List<OrderDetail>>(HttpStatus.NOT_FOUND);
		    }      
	}
	
	
	
	@GetMapping("/login/{emailId}")
	public ResponseEntity<String> get(@PathVariable String emailId) {
	    try {
	    	String token = "InvalidUser";
	        User user = userRepository.findByEmail(emailId);
	        
	        if(user !=null) {
	        	
	        	token = "oocnxm" + Math.random() + "dasd";
	        	tokenMap.put(emailId, token);
	        	
	        }
	        
	        return new ResponseEntity<String>(token, HttpStatus.OK);
	    } catch (NoSuchElementException e) {
	        return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	    }      
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody User user) {
	    try {
	    	
	    	//Hash Password
	    	String hashCodeFromUI = user.getPassword().hashCode()+"";
	         User userFromDb = userRepository.findByEmail(user.getEmail());
	         
	         if(userFromDb == null) {
	        	 return new ResponseEntity<String>("User not found", HttpStatus.OK);
	         }
	         
	         String hashCodeFromDb = userFromDb.getPassword();
	        
	         
	         String token = "InvalidUser"; 
	        if(hashCodeFromUI.equals(hashCodeFromDb)) {
	        	
	        	token = "oocnxm" + Math.random() + "dasd";
	        	tokenMap.put(user.getEmail(), token);
	        	
	        }
	        
	        return new ResponseEntity<String>(token, HttpStatus.OK);
	    } catch (NoSuchElementException e) {
	        return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	    }      
	}
	
	
	
}
