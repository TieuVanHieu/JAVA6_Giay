package com.example.demo.servicelmpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CartEntity;
import com.example.demo.repository.CartEntityDAO;
import com.example.demo.service.CartService;

/**
 * CartServiceImpl
 */
@Service
public class CartServiceImpl implements CartService {
    // @Autowired
    // CartEntityDAO cartEntityDAO;

    // @Override
    // public CartEntity findByUserUserId(Integer id) {
    //     return cartEntityDAO.findByUserUserId(id);
    // }

    // @Override
    // public CartEntity save(CartEntity entity) {
    //     return cartEntityDAO.save(entity);
    // }

}
