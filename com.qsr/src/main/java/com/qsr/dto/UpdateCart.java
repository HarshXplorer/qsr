package com.qsr.dto;
import lombok.Data;


public class UpdateCart {
    private int quantity;
    private Long productId;

    public UpdateCart(){

    }

    public UpdateCart(int quantity, Long productId) {
        this.quantity = quantity;
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}