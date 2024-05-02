package com.qsr.service;

import com.qsr.dao.ProductCategoryRepository;
import com.qsr.dao.ProductRepository;
import com.qsr.entity.Product;
import com.qsr.entity.ProductCategory;
import com.qsr.exception.ThrowValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductCategoryRepository productCategoryRepository;
    public List<Product>retrieveAllProduct(){
        List<Product> productList=productRepository.findAll();
        if(productList.isEmpty()){
            throw new ThrowValidException("There are no products");

        }
        return productList;
    }
    public Product retrieveProductById(Long id){
        Optional<Product> product = productRepository.findById(id);
        try{
            return product.get();

        }catch (Exception e){
            throw new ThrowValidException("Product not exists");
        }
    }
    public Product createProduct(Product product){
        if(product.getId() != null){
            try{
                productRepository.findById(product.getId()).get();
                return productRepository.save(product);
            }catch (Exception e){
                throw new ThrowValidException("Product cannot be found so cant be updated");
            }
        }
        Long id = product.getCategory().getId();
        Optional<ProductCategory> category = productCategoryRepository.findById(id);
        try{
            category.get();
            return  productRepository.save(product);
        }catch(Exception e){
            throw new ThrowValidException("Category is not in record");
        }
    }
    public void removeProduct(Long id){
        try{
            productRepository.findById(id).get();
            productRepository.deleteById(id);
        }catch(Exception e){
            throw new ThrowValidException("Id:"+id+"Invalid Product");
        }
    }


}
