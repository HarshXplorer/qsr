package com.qsr.controller;

import com.qsr.entity.Order;
import com.qsr.entity.User;
import com.qsr.service.OrderService;
import com.qsr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

	@Autowired
	private OrderService service;
	
	@Autowired
	private UserService us;
	
	//Get all Orders
	@GetMapping("/orders")
	public List<Order> retrieveAllOrders(){
		 List<Order> OrderList = service.retrieveAllOrders();
		 return OrderList;
	}
	
	//Get Order by id
	@GetMapping("/orders/{id}")
	public Order retrieveOrder(@PathVariable Long id){
		Order order2 = service.retrieveOrderById(id); 
		 return order2;
	}
	
	// Create new Order
	@PostMapping("/orders/add/{userId}")
	public Order saveOrder(@PathVariable long userId){

		Order order2 = service.placeOrder(userId);
		 User user = us.findOne(userId);
//		 user.setTotalOrder((user.getTotalOrder()!=0?user.getTotalOrder():0)+1);
		 us.update(user);
		 return order2;
	}


//	@PostMapping("/add/{id}")
//	public Order createOrder(@PathVariable Long id) {
//
//		Order obj =service.placeOrder(id);
//		User user = us.findOne(obj.getUser().getId());
//		user.setTotalOrder((user.getTotalOrder() != 0 ? user.getTotalOrder() : 0) + 1);
//		us.save(user);
//		return obj;
//	}
		// Delete a order
	@DeleteMapping("/orders/{id}")
	public String deleteOrder(@PathVariable Long id) {
		service.removeOrder(id);
		return "Order record get deleted";
	}
	
//	@PutMapping("/orders")
//     public Order updateOrder(@RequestBody Order order){
//
//		Order order2 = service.updateOrder(order);
//
//		 return order2;
//	}
}
