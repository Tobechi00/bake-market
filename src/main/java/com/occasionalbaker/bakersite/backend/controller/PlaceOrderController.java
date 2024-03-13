package com.occasionalbaker.bakersite.backend.controller;

import com.occasionalbaker.bakersite.backend.entity.Order;
import com.occasionalbaker.bakersite.backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PlaceOrderController {

    OrderRepository orderRepository;

    @Autowired
    public PlaceOrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void  placeOrder(Order order){

    }
}
