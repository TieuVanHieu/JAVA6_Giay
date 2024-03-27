package com.example.demo.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ProductDetailEntity;
import com.example.demo.service.ProductDetailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin("*")
@RestController
@RequestMapping("/rest/products")
public class ProductRestController {
    @Autowired
    ProductDetailService productDetailService;

    @GetMapping("/{color}&{size}&{qty}&{proId}")
    public ProductDetailEntity getMethodName(
        @PathVariable("color") Integer color, 
        @PathVariable("size") Integer size,
        @PathVariable("proId") Integer proId, 
        @PathVariable("qty") Integer qty){
        ProductDetailEntity entity = productDetailService.findByColorColorIdAndSizeSizeIdAndProductProductId(color, size, proId);
        return entity;
    }
    
}
