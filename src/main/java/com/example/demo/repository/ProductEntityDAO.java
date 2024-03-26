package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.BrandEntity;
import com.example.demo.model.CategoryEntity;
import com.example.demo.model.ProductEntity;

/**
 * ProductEntityDAO
 */

public interface ProductEntityDAO extends JpaRepository<ProductEntity, Integer> {
    // // user
    List<ProductEntity> findByCategoryCategoryId(Integer id);
    // // tìm tất cả sản phẩm
    // Page<ProductEntity> findAll(Pageable pageable);

    // // tìm sản phẩm theo categoryid
    Page<ProductEntity> findByCategoryCategoryId(Optional<Integer> categoryId, Pageable page);

    // // tìm sản phẩm theo categoryid và giá trong khoản min max
    // Page<ProductEntity> findByCategoryCategoryIdAndProductPriceBetween(Integer categoryId, Double minPrice,
    //         Double maxPrice, Pageable page);

    // // tìm sản phẩm trong giá min max
    // Page<ProductEntity> findByProductPriceBetween(Double minPrice, Double maxPrice, Pageable pageable);

    // // tìm sản phẩm theo brand
    // Page<ProductEntity> findByBrand(BrandEntity brand, Pageable pageable);

    // // tìm sản phẩm theo tên
    // Page<ProductEntity> findAllByProductNameLike(String productName, Pageable pageable);

    // // tìm sản phẩm theo brandid
    // List<ProductEntity> findByBrandBrandId(Integer brandId);

    // List<ProductEntity> findByCategory(CategoryEntity category);

    // // user
}
