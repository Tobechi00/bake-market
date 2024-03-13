package com.occasionalbaker.bakersite.backend.service;

import com.occasionalbaker.bakersite.backend.entity.Order;
import com.occasionalbaker.bakersite.backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyOrderService {

    OrderRepository orderRepository;

    @Autowired
    public MyOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> listOrderByUsername(String username){
        return orderRepository.findByUsername(username);
    }
}
