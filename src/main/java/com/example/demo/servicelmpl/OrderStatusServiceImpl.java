package com.example.demo.servicelmpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.OrderStatusEntity;
import com.example.demo.model.ShippingInfoEntity;
import com.example.demo.repository.OrderStatusEntityDAO;
import com.example.demo.service.OrderDetailService;
import com.example.demo.service.OrderStatusService;

/**
 * OrderStatusServiceImpl
 */
@Service
public class OrderStatusServiceImpl implements OrderStatusService {
    // @Autowired
    // OrderStatusEntityDAO orderStatusEntityDAO;

    // @Override
    // public Optional<OrderStatusEntity> findById(Integer id) {
    //     return orderStatusEntityDAO.findById(id);
    // }

}
