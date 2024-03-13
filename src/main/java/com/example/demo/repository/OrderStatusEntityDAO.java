package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.OrderStatusEntity;


/**
 * OrderStatusEntityDAO
 */
public interface OrderStatusEntityDAO extends JpaRepository<OrderStatusEntity, Integer> {
    // List<OrderStatusEntity> findByStatusId(Integer statusId);
  
}
