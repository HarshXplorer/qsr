package com.qsr.dao;

import com.qsr.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//@Transactional
public interface ProductRepository extends JpaRepository<Product,Long> {
    void deleteByCategoryId(Long id);
}
