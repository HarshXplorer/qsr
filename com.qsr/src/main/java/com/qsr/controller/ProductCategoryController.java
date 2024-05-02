package com.qsr.controller;

//import com.ecommerce.Iservice.IProductCategoryService;
import com.qsr.entity.ProductCategory;
import com.qsr.service.ProductcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductCategoryController {

	@Autowired
	ProductcategoryService service;
	
	//Get all Category
	@GetMapping("/categories/all")
	public ResponseEntity<List<ProductCategory>> retrieveAll(){
		 List<ProductCategory> categoryList = service.retrieveAll();
		 return new ResponseEntity<>(categoryList,HttpStatus.OK);
	}
	
	//Get Category By Id
	@GetMapping("/categories/{id}")
	public ResponseEntity<ProductCategory> retrieveCategoryById(@PathVariable Long id){
		 ProductCategory category = service.retrieveById(id);
		 return new ResponseEntity<>(category,HttpStatus.OK);
	}
	
	//Create new Category
	@PostMapping("/add/categories")
	public ResponseEntity<ProductCategory> creatCategory(@RequestBody ProductCategory obj){
		 ProductCategory category = service.createProductCategory(obj);
		 return new ResponseEntity<>(category,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/categories/delete/{id}")
	public String removeCategory(@PathVariable Long id){
		service.deleteCategory(id);
		return "Category removed from data";
	}
	
}
