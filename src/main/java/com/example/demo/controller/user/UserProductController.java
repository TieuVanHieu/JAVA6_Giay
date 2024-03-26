package com.example.demo.controller.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.ProductDetailEntity;
import com.example.demo.model.ProductEntity;
import com.example.demo.service.ProductDetailService;
import com.example.demo.service.ProductService;

@Controller
public class UserProductController {
    @Autowired
    ProductService productService;
	@Autowired
	ProductDetailService productDetailService;

    @RequestMapping("/product")
	public String category(@RequestParam("p") Optional<Integer> p,@RequestParam("cid") Optional<Integer> cid,Model model) {
        Pageable pageable = PageRequest.of(p.orElse(0), 9);
        if (cid.isPresent()) {
            Page<ProductEntity> page = productService.findByCategoryId(cid,pageable);
            model.addAttribute("items", page);

        }else{
            Page<ProductEntity> page = productService.findAll(pageable);
            model.addAttribute("items", page);
        } 
		return "user/category";
	}

    @RequestMapping("/product/detail/{id}")
	public String product(Model model,@PathVariable("id") Integer id) {
		int count = 1;
        List<Map<String, Object>> colors = new ArrayList<>();
        List<Map<String, Object>> sizes = new ArrayList<>();
        model.addAttribute("productDetails", productDetailService.findByProductId(id));
        
		for (ProductDetailEntity productDetail : productDetailService.findByProductId(id)) {
            if (count == 1) {
				model.addAttribute("items", productService.findByCategoryIdList(productDetail.getProduct().getCategory().getCategoryId()));
                model.addAttribute("detail_id", productDetail.getProduct().getProductId());
                model.addAttribute("detail_image", productDetail.getProduct().getProductImages());
                model.addAttribute("detail_name", productDetail.getProduct().getProductName());
                model.addAttribute("detail_price", productDetail.getProduct().getProductPrice());
				model.addAttribute("detail_description", productDetail.getProduct().getProductDescription());
                // model.addAttribute("proBrand",
                //         productService.findByBrandBrandId(productDetail.getProduct().getBrand().getBrandId()));
                
            }
            Integer colorId = productDetail.getColor().getColorId();
            String colorName = productDetail.getColor().getColorName();

            if (!colors.stream().anyMatch(map -> map.containsKey("colorId") && map.get("colorId").equals(colorId))) {
                Map<String, Object> colorInfo = new HashMap<>();
                colorInfo.put("colorId", colorId);
                colorInfo.put("colorName", colorName);
                colors.add(colorInfo);
            }

            Integer sizeId = productDetail.getSize().getSizeId();
            String sizeName = productDetail.getSize().getSizeName();

            if (!sizes.stream().anyMatch(map -> map.containsKey("sizeId") &&
                    map.get("sizeId").equals(sizeId))) {
                Map<String, Object> sizeInfo = new HashMap<>();
                sizeInfo.put("sizeId", sizeId);
                sizeInfo.put("sizeName", sizeName);
                sizes.add(sizeInfo);
            }  

            count++;
        }

        model.addAttribute("sizes", sizes);
        model.addAttribute("colors", colors);
		
		return "user/product";
	}
}   
