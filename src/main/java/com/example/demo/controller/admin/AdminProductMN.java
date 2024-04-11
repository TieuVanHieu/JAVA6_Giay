package com.example.demo.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Locale.Category;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jersey.JerseyProperties.Servlet;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.BrandEntity;
import com.example.demo.model.CategoryEntity;
import com.example.demo.model.ProductEntity;
import com.example.demo.repository.BrandEntityDAO;
import com.example.demo.repository.CategoryEntityDAO;
import com.example.demo.repository.ProductDetailEntityDAO;
import com.example.demo.repository.ProductEntityDAO;
import com.example.demo.service.ParamService;
import com.example.demo.service.ProductService;
import javax.servlet.ServletContext;
import javax.servlet.annotation.MultipartConfig;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@MultipartConfig
@RequestMapping("/product-admin")
public class AdminProductMN {
    @Autowired
    ProductService productService;

    @Autowired
    ProductEntityDAO productEntityDAO;

    @Autowired
    CategoryEntityDAO categoryEntityDAO;

    @Autowired
    BrandEntityDAO brandEntityDAO;

    @Autowired
    ProductDetailEntityDAO productDetailEntityDAO;

    @Autowired
    ParamService paramService;

    @Autowired
    ServletContext app;

    @RequestMapping({ "" })
    public String showProduct1(Model model) {
        ProductEntity productEntity = new ProductEntity();
        model.addAttribute("product", productEntity);
        List<ProductEntity> productEntity1 = productEntityDAO.findAll();
        model.addAttribute("product1", productEntity1);
        return "admin/product-management";
    }

    @RequestMapping("/form-product")
    public String ShowPro(Model model) {
        List<CategoryEntity> CategoryEntity = categoryEntityDAO.findAll();
        model.addAttribute("category", CategoryEntity);
        model.addAttribute("product", new ProductEntity());
        return "/admin/form-product";
    }

    @RequestMapping("/edit/{productId}")
    public String edit(Model model, @PathVariable("productId") Integer productId) {
        ProductEntity productEntity = productEntityDAO.findById(productId).get();
        model.addAttribute("product", productEntity);
        List<ProductEntity> productEntity1 = productEntityDAO.findAll();
        model.addAttribute("product1", productEntity1);
        return "/admin/form-product";
    }

@RequestMapping("/create")
public String create(Model model, @Validated @ModelAttribute("product") ProductEntity productEntity,
        BindingResult result,
        @RequestParam("picture") MultipartFile multipartFile) throws IllegalStateException, IOException {
    if (result.hasErrors()) {
        // If there are validation errors, return to the product creation page with error messages
        return "/admin/form-product";
    }

    try {
        // Check if the product name is empty
        if (productEntity.getProductName() == null || productEntity.getProductName().isEmpty()) {
            // If the product name is empty, add an error to the BindingResult
            result.rejectValue("productName", "NotEmpty.productEntity.productName", "Tên sản phẩm không được bỏ trống");
            // Return to the product creation page with the error message
            return "/admin/form-product";
        }
        
        // Check if the product price is empty
        if (productEntity.getProductPrice() == null) {
            // If the product price is empty, add an error to the BindingResult
            result.rejectValue("productPrice", "NotEmpty.productEntity.productPrice", "Giá sản phẩm không được bỏ trống");
            // Return to the product creation page with the error message
            return "/admin/form-product";
        }
        if (productEntity.getProductPrice() <= 999) {
            // If the product price is empty, add an error to the BindingResult
            result.rejectValue("productPrice", "NotEmpty.productEntity.productPrice", "Giá sản phẩm phải từ 1000 VNĐ");
            // Return to the product creation page with the error message
            return "/admin/form-product";
        }
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String uploadDir = "/images";
        productEntity.setProductImages(fileName);
        productEntityDAO.save(productEntity);
        paramService.save(multipartFile, uploadDir);
        model.addAttribute("product", new ProductEntity());
    } catch (Exception e) {
        // Handle exceptions if needed
    }
    return "redirect:/product-admin";
}

    @ModelAttribute("categoryIds")
    public List<CategoryEntity> getAllCategoryIds() {
        List<CategoryEntity> categories = categoryEntityDAO.findAll();
        // List<Integer> categoryIds = categories.stream().map(CategoryEntity::getCategoryId).collect(Collectors.toList());
        return categories;
    }

    @ModelAttribute("brandIds")
    public List<BrandEntity> getAllBrandIds() {
        List<BrandEntity> brands = brandEntityDAO.findAll();
        // List<Integer> brandIds = brands.stream().map(BrandEntity::getBrandId).collect(Collectors.toList());
        return brands;
    }

    @PostMapping("/addCategory")
    public String addCate(@ModelAttribute("category") CategoryEntity categoryEntity) {
        categoryEntityDAO.save(categoryEntity);
        
        return "redirect:/product-admin/form-product";
    }
    
    @PostMapping("/addBrand")
    public String addBrand(@ModelAttribute("brand") BrandEntity brandEntity) {
        brandEntityDAO.save(brandEntity);
        
        return "redirect:/product-admin/form-product";
    }
    

  

@RequestMapping("/delete/{productId}")
    public String delete(@PathVariable("productId") Integer productId,Model model, RedirectAttributes redirectAttributes) {
        Optional<ProductEntity> productOptional = productEntityDAO.findById(productId);
        
        if (productOptional.isPresent()) {
            ProductEntity productEntity = productOptional.get();
            
            // Kiểm tra xem có sản phẩm nào liên kết với hãng không
            if (!productEntity.getProductDetail().isEmpty()) {
                // Nếu có, không xóa và thông báo lỗi
                // redirectAttributes.addFlashAttribute("error", "Không thể xóa hãng này vì có sản phẩm đang liên kết với nó.");
                model.addAttribute("messageDanger", "Không thể xóa sản phẩm này vì có sản phẩm chi tiết đang liên kết với nó.");
                return "forward:/product-admin";
            } else {
                // Nếu không có sản phẩm liên kết, xóa hãng
                productEntityDAO.deleteById(productId);
                model.addAttribute("messageSuccess", "Xóa thành công !");
                return "forward:/product-admin";
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy sản phẩm");
        }
        
        return "redirect:/product-admin";
    }


}