package com.example.demo.controller.admin;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.ColorEntity;
import com.example.demo.model.ProductDetailEntity;
import com.example.demo.model.ProductEntity;
import com.example.demo.model.SizeEntity;
import com.example.demo.repository.CartEntityDAO;
import com.example.demo.repository.ColorEntityDAO;
import com.example.demo.repository.OrderDetailEntityDAO;
import com.example.demo.repository.ProductDetailEntityDAO;
import com.example.demo.repository.ProductEntityDAO;
import com.example.demo.repository.SizeEntityDAO;
import com.example.demo.service.ParamService;
import com.example.demo.service.ProductDetailService;

import javax.servlet.ServletContext;

@Controller
@RequestMapping("/productDetail")
public class AdminProductDetailMN {
    @Autowired
    ProductDetailService productDetailService;

    @Autowired
    ProductDetailEntityDAO productDetailEntityDAO;

    @Autowired
    ProductEntityDAO productEntityDAO;

    @Autowired
    SizeEntityDAO sizeEntityDAO;

    @Autowired
    ColorEntityDAO colorEntityDAO;

    @Autowired
    CartEntityDAO cartEntityDAO;

    @Autowired
    OrderDetailEntityDAO orderDetailEntityDAO;

    @Autowired
    ParamService paramService;

    @Autowired
    ServletContext app;

    @RequestMapping({ "" })
    public String showProDe1(Model model) {
        ProductDetailEntity productDetailEntity = new ProductDetailEntity();
        model.addAttribute("productDetail", productDetailEntity);
        List<ProductDetailEntity> productDetailEntity1 = productDetailEntityDAO.findAll();
        model.addAttribute("productDetail1", productDetailEntity1);
        return "/admin/productDetail-management";
    }

    @RequestMapping("/form-productDetail")
    public String showFPD(Model model) {
        model.addAttribute("productDetail", new ProductDetailEntity());
        // model.addAttribute(null, ProductDetailEntity)
        return "admin/form-productDetail";
    }

    @RequestMapping("/form-productDetailId/{Id}")
    public String showPDId(Model model, @PathVariable("Id") Integer Id) {
        List<ProductDetailEntity> productDetailEntity1 = productDetailEntityDAO.findByProductProductId(Id);
        model.addAttribute("productDetail1", productDetailEntity1);
        System.out.println(Id);
        return "admin/productDetail-management";
    }

    @RequestMapping("/edit/{productDetailId}")
    public String edit(Model model, @PathVariable("productDetailId") Integer productDetailId) {
        ProductDetailEntity productDetailEntity = productDetailEntityDAO.findById(productDetailId).get();
        model.addAttribute("productDetail", productDetailEntity);
        List<ProductDetailEntity> productDetailEntity1 = productDetailEntityDAO.findAll();
        model.addAttribute("productDetail1", productDetailEntity1);
        return "/admin/form-productDetail";
    }
    @RequestMapping("/create")
    public String create(Model model,  @ModelAttribute("productDetail") ProductDetailEntity productDetailEntity, BindingResult result) { 
        try {
            productDetailEntityDAO.save(productDetailEntity);
            model.addAttribute("productDetail", new ProductDetailEntity());
        } catch (Exception e) {
            // Handle exceptions if needed
        }
        return "redirect:/productDetail";
    }
    @ModelAttribute("sizeIds")
    public List<SizeEntity> getAllCategoryIds() {
        List<SizeEntity> sizeEntity = sizeEntityDAO.findAll();
        return sizeEntity;
    }

    @ModelAttribute("colorIds")
    public List<ColorEntity> getAllBrandIds() {
        List<ColorEntity> colorEntity = colorEntityDAO.findAll();
        return colorEntity;
    }

    @ModelAttribute("productIds")
    public List<ProductEntity> getAllProductIds() {
        List<ProductEntity> productEntity = productEntityDAO.findAll();
        return productEntity;
    }

    @PostMapping("/addSize")
    public String addSize(@ModelAttribute("size") SizeEntity sizeEntity) {
        sizeEntityDAO.save(sizeEntity);

        return "redirect:/productDetail/form-productDetail";
    }

    @PostMapping("/addColor")
    public String addColor(@ModelAttribute("color") ColorEntity colorEntity) {
        colorEntityDAO.save(colorEntity);

        return "redirect:/productDetail/form-productDetail";
    }

    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute("product") ProductEntity productEntity) {
        productEntityDAO.save(productEntity);

        return "redirect:/productDetail/form-productDetail";
    }

}
