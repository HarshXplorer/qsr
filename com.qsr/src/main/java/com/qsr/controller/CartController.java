package com.qsr.controller;

import com.qsr.dto.CartDto;
import com.qsr.dto.UpdateCart;
import com.qsr.service.CartService;
import com.qsr.entity.Cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {
	
	@Autowired
	private CartService service;
	
	@GetMapping("/carts/all")
	public ResponseEntity<List<Cart>> retrieveAll(){
		List<Cart> cartList = service.retrieveAll();
		return new ResponseEntity<>(cartList,HttpStatus.OK);
	}
	
	@PostMapping("/carts/add")
	public ResponseEntity<Cart> addToCart(@RequestBody CartDto cart){
		Cart cart2 = service.createCart(cart);
		return new ResponseEntity<>(cart2,HttpStatus.OK);
	}
	
	@DeleteMapping("/carts/delete/{id}")
	public String removeCartItem(@PathVariable Long id) {
		      service.removeCart(id);
		return "Item removed from cart";
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Cart> updatecart(@PathVariable Long id,@RequestBody UpdateCart updateCart){
		Cart cart2 = service.updateCart(id,updateCart);
		return new ResponseEntity<>(cart2,HttpStatus.OK);
	}

}
