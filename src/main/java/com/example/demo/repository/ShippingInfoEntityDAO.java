package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.OrderEntity;
import com.example.demo.model.ShippingInfoEntity;
import java.util.List;

/**
 * ShippingInfoEntityDAO
 */
public interface ShippingInfoEntityDAO extends JpaRepository<ShippingInfoEntity, Integer> {
    // List<ShippingInfoEntity> findByOrderInfoOrderId(Integer orderId);
}