package com.qsr.controller;

import com.qsr.service.ProductService;
import com.qsr.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

	@Autowired
	ProductService service;
	
	
	//Get product List
	@GetMapping("/products/all")
	public ResponseEntity<List<Product>> retrieveAll(){
		 List<Product> productList = service.retrieveAllProduct();
		 return new ResponseEntity<>(productList,HttpStatus.OK);
	}
	
	//Get product By Id
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> retrieveProductById(@PathVariable Long id){
		 Product product = service.retrieveProductById(id);	 
		 return new ResponseEntity<>(product,HttpStatus.OK);
	}
	
	//Add New Product
	@PostMapping("/products/add")
	public ResponseEntity<Product> addProduct(@RequestBody Product obj){
		Product createProduct = service.createProduct(obj);
		return new ResponseEntity<>(createProduct,HttpStatus.CREATED);
	}
	
	//Delete Product by Id
	@DeleteMapping("/products/delete/{id}")
	public String deleteProduct(@PathVariable Long id){
		service.removeProduct(id);
		return "Product removed";
	}
	
	
}
