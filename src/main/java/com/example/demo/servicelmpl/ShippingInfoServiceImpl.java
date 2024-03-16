package com.example.demo.servicelmpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ShippingInfoEntity;
import com.example.demo.repository.ShippingInfoEntityDAO;
import com.example.demo.service.ShippingInfoService;

/**
 * ShippingInfoServiceImpl
 */
@Service
public class ShippingInfoServiceImpl implements ShippingInfoService {
    // @Autowired
    // ShippingInfoEntityDAO shippingInfoEntityDAO;

    // @Override
    // public ShippingInfoEntity save(ShippingInfoEntity entity) {
    //     return shippingInfoEntityDAO.save(entity);
    // }

    // @Override
    // public List<ShippingInfoEntity> findByOrderOrderId(Integer orderId) {
    //     return shippingInfoEntityDAO.findByOrderInfoOrderId(orderId);
    // }

    // @Override
    // public void delete(ShippingInfoEntity entity) {
    //     shippingInfoEntityDAO.delete(entity);
    // }
}
