package com.qsr.service;

import com.qsr.dao.CartRepository;
import com.qsr.dao.ProductRepository;
import com.qsr.dao.UserRepository;
import com.qsr.dto.CartDto;
import com.qsr.dto.UpdateCart;
import com.qsr.entity.Cart;
import com.qsr.entity.Product;
import com.qsr.entity.User;
import com.qsr.exception.ThrowValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CartDto cartDto;

    public List<Cart> retrieveAll() {
        List<Cart> cartList = cartRepository.findAll();
        if (cartList.isEmpty()) {
            throw new ThrowValidException("No Item Is Selected");
        }
        return cartList;
    }

//    public Cart createCart(Cart obj) {
//        try {
//            List<Cart> cartList = cartRepository.findAll();
//            if (cartList != null) {
//                for (Cart i : cartList) {
//                    if (i.getUser().getId() == obj.getUser().getId()) {
//                        if (i.getProduct().getId() == obj.getProduct().getId()) {
//                            Double price = i.getPrice();
//                            Product product = productRepository.findById(obj.getProduct().getId()).get();
//                            Double productPrice = product.getProductPrice();
//                            i.setPrice(productPrice * (obj.getQuantity()) + (price));
//                            i.setQuantity(obj.getQuantity() + i.getQuantity());
//                            return cartRepository.save(i);
//                        }
//
//                    }
//                }
//            }
//            Product product = productRepository.findById(obj.getProduct().getId()).get();
//            Double productPrice = product.getProductPrice();
//            obj.setPrice(productPrice * (obj.getQuantity()));
//            obj.setProduct(product);
//            obj.setUser(userRepository.findById(obj.getUser().getId()).get());
//            Cart cart = cartRepository.save(obj);
//            return cart;
//        } catch (Exception e) {
//            throw new ThrowValidException("Select product not exists in database");
//        }
//
//    }
    public Cart createCart(CartDto cartDto){
        try {
            Product product = productRepository.findById(cartDto.getProductId()).get();
            User user = userRepository.findById(cartDto.getUserId()).get();
            Cart cartObj = new Cart(cartDto.getQuantity(), product.getProductPrice(), user, product, cartDto.getCurrentDate());
            List<Cart> cartList = cartRepository.findAll();
            if (cartList != null) {
                for (Cart existingCart : cartList) {
                    if (existingCart.getUser().getId() == cartObj.getUser().getId()) {
                        if (existingCart.getProduct().getId() == cartObj.getProduct().getId()) {
                            double price = existingCart.getPrice();
                            double productPrice = product.getProductPrice();
                            existingCart.setPrice((productPrice * cartObj.getQuantity()) + price);
                            existingCart.setQuantity(cartObj.getQuantity() + existingCart.getQuantity());
                            return cartRepository.save(existingCart);
                        }
                    }
                }
            }


            double price = product.getProductPrice();
            cartObj.setPrice(price * cartObj.getQuantity());
            cartObj.setProduct(product);
           // cartObj.setUser(userRepository.findById(cartObj.getUser().getId()).get());
            cartObj.setUser(user);
            Cart cart = cartRepository.save(cartObj);
            return cart;
        }catch(Exception e){
            throw new ThrowValidException("The selected product does not exist");
        }
    }

    public void removeCart(Long id){
        try {
            Cart cart = cartRepository.findById(id).get();
            cartRepository.deleteById(id);
        }catch(Exception e){
            throw new ThrowValidException("Sorry something went wrong");
        }
    }
    public Cart updateCart(Long id, UpdateCart updateCart){
        try{
            Cart cartObj = cartRepository.findById(id).get();
            Product product = productRepository.findById(updateCart.getProductId()).get();
            Double productPrice = product.getProductPrice();
            cartObj.setPrice(productPrice*(cartObj.getQuantity()));
            Cart cart = cartRepository.save(cartObj);
            return cart;

        }catch (Exception e){
            throw new ThrowValidException("Something is wrong with input ");
        }
    }

}
