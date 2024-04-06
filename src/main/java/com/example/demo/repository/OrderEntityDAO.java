package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.OrderEntity;

/**
 * OrderEntityDAO
 */
public interface OrderEntityDAO extends JpaRepository<OrderEntity, Integer> {
    // // user
    // List<OrderEntity> findByUserUserId(Integer userId);

    // // timtheo id
    // OrderEntity findByOrderId(Integer orderId);
    // // user

    // List<OrderEntity> findByOrderStatusStatusId(Integer statusId);
    // @Query("SELECT SUM(o.totalAmount) FROM OrderEntity o")
    // double calculateTotalIncome();
}
