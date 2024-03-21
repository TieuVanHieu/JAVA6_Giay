package com.example.demo.service;

import java.util.List;



import com.example.demo.model.ProductDetailEntity;

/**
 * ProductDetailService
 */

public interface ProductDetailService {
    // // user

    // tìm productdeetail theo productid
    List<ProductDetailEntity> findByProductId(Integer id);

    // // tìm productdeetail theo productid color size
    // ProductDetailEntity findByColorColorIdAndSizeSizeIdAndProductProductId(Integer colorId, Integer sizeId,
    //         Integer productId);

    // // tìm productdeetail theo cart
    // // user
}