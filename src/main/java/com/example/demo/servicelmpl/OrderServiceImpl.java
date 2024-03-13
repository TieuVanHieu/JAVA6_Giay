package com.example.demo.servicelmpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.OrderEntity;
import com.example.demo.repository.OrderEntityDAO;
import com.example.demo.service.OrderService;

/**
 * OrderServiceImpl
 */
@Service
public class OrderServiceImpl implements OrderService {
    // @Autowired
    // OrderEntityDAO orderEntityDAO;

    // @Override
    // public OrderEntity save(OrderEntity order) {
    //     return orderEntityDAO.save(order);
    // }

    // @Override
    // public List<OrderEntity> findByUserUserId(Integer userId) {
    //     return orderEntityDAO.findByUserUserId(userId);
    // }

    // @Override
    // public OrderEntity findByOrderId(Integer orderId) {
    //     return orderEntityDAO.findByOrderId(orderId);
    // }

    // @Override
    // public void delete(OrderEntity order) {
    //     orderEntityDAO.delete(order);
    // }

}
