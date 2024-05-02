package com.qsr.service;

import com.qsr.dao.*;
import com.qsr.entity.*;
import com.qsr.exception.ThrowValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    OrderItemsRepository orderItemsRepository;
    @Autowired
    UserRepository userRepository;
    public List<Order> retrieveAllOrders(){
        List<Order> orderList = orderRepository.findAll();
        if(orderList.isEmpty()){
            throw new ThrowValidException("There are no orders yet");
        }
        return orderList;
    }
    public Order retrieveOrderById(Long id){
        try{
            Order order = orderRepository.findById(id).get();
            return order;

        }catch(Exception e){
            throw new ThrowValidException("Wrong order id:"+ id);

        }
    }
    public Order placeOrder(Long userId){

        Order order = new Order();
        User user = userRepository.findById(userId).get();
        List<Order>  orderList = orderRepository.findAll();
        List<Cart> cartList = cartRepository.findByUserId(userId);
        Integer quantity=0;
        double productPrice = 0;
        for(Cart i: cartList) {
            quantity += i.getQuantity();
            productPrice = i.getPrice()+productPrice;
        }
        order.setTotalQuantity(quantity);
        order.setTotalPrice(productPrice);
        order.setStatus("On to The Kitchen");
        order.setUser(userRepository.findById(userId).get());
        Order orders =orderRepository.save(order);

        for(Cart i: cartList){
            OrderItems orderItems = new OrderItems(i.getQuantity(), i.getPrice(),orders,i.getProduct());
            Product product =productRepository.findById(i.getProduct().getId()).get();
            productRepository.save(product);
            orderItemsRepository.save(orderItems);
        }
        cartRepository.deleteById(userId);
        return orders;
    }



//    public Order placeOrder(Long userId)
//    {
//        User user = userRepository.findById(userId).get();
//        //List<Order> orderList = orderRepository.findAll();
//        Order order = new Order();
//        List<Cart> cartList = cartRepository.findByUserId(user.getId());
//        int quantity = 0;
//        double price = 0;
//        double price_after_discount;
//        for(Cart exists:cartList)
//        {
//            quantity+=exists.getQuantity();
//            price += exists.getPrice();
//        }
//        if(price<1000){
//            price_after_discount=price;
//        }
//        else if(price>1000 && price<=2000){
//            price_after_discount=price-0.05*price;
//        }
//        else if(price>2000 && price<=3000){
//            price_after_discount=price-0.8*price;
//        }
//        else{
//            price_after_discount=price-0.1*price;
//        }
//
//        order.setTotalQuantity(quantity);
//        order.setTotalPrice(price_after_discount);
//        order.setUser(userRepository.findById(userId).get());
////        order.setDate(LocalDate.now());
//        order.setStatus("Success");
//        Order order1 = orderRepository.save(order);
//        for(Cart exists:cartList)
//        {
//            OrderItems orderItem = new OrderItems(exists.getQuantity(),exists.getPrice(),order1,exists.getProduct());
////            int quantity, double price, Order orders, Product product
//            Product product = productRepository.findById(exists.getProduct().getId()).get();
////            product.setStock(product.getStock()-exists.getQuantity());
//            productRepository.save(product);
//            orderItemsRepository.save(orderItem);
//        }
//        cartRepository.deleteByUserId(userId);
//        return order1;
//    }

    public void removeOrder(Long id) {
        retrieveOrderById(id);
        orderRepository.deleteById(id);
    }
    public Order update(Order order){
        return orderRepository.save(order);
    }
}
