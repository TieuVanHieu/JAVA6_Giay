package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.OrderDetailEntity;
import com.example.demo.model.OrderEntity;

import java.util.List;
import java.util.Optional;

/**
 * OrderDetailEntityDAO
 */
public interface OrderDetailEntityDAO extends JpaRepository<OrderDetailEntity, Integer> {
    
    
    // List<OrderDetailEntity> findByOrderOrderId(Integer orderId);


}
