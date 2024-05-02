package com.qsr.dao;

import com.qsr.entity.Cart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@Transactional
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUserId(Long Id);

    void deleteByUserId(Long Id);
}
