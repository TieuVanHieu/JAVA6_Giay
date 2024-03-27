package com.example.demo.servicelmpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.ProductEntity;
import com.example.demo.repository.ProductEntityDAO;
import com.example.demo.service.ProductService;

/**
 * ProductServiceImpl
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductEntityDAO productEntityDAO;

    // @Override
    // public List<ProductEntity> findAll() {
    //     return productEntityDAO.findAll();
    // }

    @Override
    public List<ProductEntity> findByCategoryIdList(Integer id) {
        return productEntityDAO.findByCategoryCategoryId(id);
    }

    @Override
    public Page<ProductEntity> findAll(Pageable pageable) {
        return productEntityDAO.findAll(pageable);
    }

    // @Override
    // public Page<ProductEntity> findAllAndPrice(Double minPrice, Double maxPrice, Pageable pageable) {
    //     return productEntityDAO.findByProductPriceBetween(minPrice, maxPrice, pageable);
    // }

    @Override
    public Page<ProductEntity> findByCategoryId(Optional<Integer> categoryId, Pageable pageable) {
        return productEntityDAO.findByCategoryCategoryId(categoryId, pageable);
    }

    @Override
    public ProductEntity findById(Integer id) {
        return productEntityDAO.findById(id).orElse(null);
    }



    // @Override
    // public Page<ProductEntity> findByCategoryIdAndPrice(Integer categoryId, Double minPrice, Double maxPrice,
    //         Pageable pageable) {
    //     return productEntityDAO.findByCategoryCategoryIdAndProductPriceBetween(categoryId, minPrice, maxPrice,
    //             pageable);
    // }

    // @Override
    // public Page<ProductEntity> findAllByProductNameLike(String productName, Pageable pageable) {
    //     return productEntityDAO.findAllByProductNameLike(productName, pageable);
    // }

    // @Override
    // public List<ProductEntity> findByBrandBrandId(Integer brandId) {
    //     return productEntityDAO.findByBrandBrandId(brandId);
    // }

    // // @Override
    // // public List<ProductEntity> findByNameCategory(String name) {
    // // return productEntityDAO.findByCategoryName(name);
    // // }

}
