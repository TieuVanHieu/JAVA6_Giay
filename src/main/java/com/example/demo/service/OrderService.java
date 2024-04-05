package com.example.demo.service;

import java.util.List;

import com.example.demo.model.OrderEntity;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * OrderService
 */

public interface OrderService {

    OrderEntity create(JsonNode orderDate);
    // // user

    OrderEntity findById(Integer id);

    List<OrderEntity> findByUsername(String username);


    // // tim order theo userid
    // List<OrderEntity> findByUserUserId(Integer userId);

    // OrderEntity save(OrderEntity order);

    // // tim theo id
    // OrderEntity findByOrderId(Integer orderId);

    // void delete(OrderEntity order);
    // // user

}