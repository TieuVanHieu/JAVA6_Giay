package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.ProductDetailEntity;
import java.util.List;

/**
 * ProductDetailEntityDAO
 */
public interface ProductDetailEntityDAO extends JpaRepository<ProductDetailEntity, Integer> {
    // // user
    // tim productdetail theo productid
    List<ProductDetailEntity> findByProductProductId(Integer id);

    // // tim productdetail theo colorid và sizeid và productid
    ProductDetailEntity findByColorColorIdAndSizeSizeIdAndProductProductId(Integer colorId, Integer sizeId,
            Integer productId);
    // // user


}
