package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.CartDetailEntity;
import java.util.List;
import java.util.Optional;

/**
 * CartDetailEntityDAO
 */
public interface CartDetailEntityDAO extends JpaRepository<CartDetailEntity, Integer> {
    // // user
    // // DSL
    // // tim cartdetail theo cartid
    // List<CartDetailEntity> findByCartCartId(Integer id);

    // // tim cartdetail theo productDetailid
    // CartDetailEntity findByProductDetialProductDetailIdAndCartCartId(Integer productDetailId, Integer cartCartId);

    // // tim so luong cardetail theo cartid
    // // jPql
    // @Query("SELECT COUNT(cd.cartDetailId) FROM CartDetailEntity cd WHERE cd.cart.cartId = ?1 ")
    // Integer findCountCartdetail(Integer id);

    // // user
}
