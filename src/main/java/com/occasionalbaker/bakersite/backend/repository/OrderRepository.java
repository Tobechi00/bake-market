package com.occasionalbaker.bakersite.backend.repository;

import com.occasionalbaker.bakersite.backend.entity.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findByUsername(String username);
}
