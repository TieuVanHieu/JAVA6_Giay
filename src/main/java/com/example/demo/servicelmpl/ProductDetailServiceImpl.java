package com.example.demo.servicelmpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ProductDetailEntity;
import com.example.demo.repository.ProductDetailEntityDAO;
import com.example.demo.service.ProductDetailService;

/**
 * ProductDetailServiceImpl
 */
@Service
public class ProductDetailServiceImpl implements ProductDetailService {
    // @Autowired
    // ProductDetailEntityDAO detailEntityDAO;

    // @Override
    // public List<ProductDetailEntity> findByProductId(Integer id) {
    //     return detailEntityDAO.findByProductProductId(id);
    // }

    // @Override
    // public ProductDetailEntity findByColorColorIdAndSizeSizeIdAndProductProductId(Integer colorId, Integer sizeId,
    //         Integer productId) {
    //     return detailEntityDAO.findByColorColorIdAndSizeSizeIdAndProductProductId(colorId, sizeId, productId);
    // }

}
