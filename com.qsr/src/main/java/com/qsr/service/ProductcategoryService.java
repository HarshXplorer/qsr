package com.qsr.service;

import com.qsr.dao.ProductCategoryRepository;
import com.qsr.entity.ProductCategory;
import com.qsr.exception.ThrowValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductcategoryService  {
    @Autowired
    ProductCategoryRepository productCategoryRepository;
    public List<ProductCategory> retrieveAll(){
        List<ProductCategory> categoryList =productCategoryRepository.findAll();
        if(categoryList.isEmpty()) {
            throw new ThrowValidException("There are no categories");

        }
        return categoryList;
    }
    public ProductCategory retrieveById(Long id){
        Optional<ProductCategory> category = productCategoryRepository.findById(id);
        try{
            return category.get();

        }catch (Exception e) {
            throw new ThrowValidException("Id:"+id+"is in wrong category");

        }
    }
    public ProductCategory createProductCategory (ProductCategory obj) {
        if (obj.getId() != null) {
            try{
                productCategoryRepository.findById(obj.getId()).get();
            }catch (Exception e){
                throw new ThrowValidException("Category not found so unable to update");
            }
        }
        ProductCategory productCategory = productCategoryRepository.save(obj);
        return  productCategory;
    }
    public void deleteCategory(Long id){
        try{
            productCategoryRepository.findById(id).get();
            productCategoryRepository.deleteById(id);
        }catch(Exception e){
            throw new ThrowValidException("Id:"+id+"Invalid Category");
        }
    }


}
