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


    @RequestMapping({""})
    public String showProDe1(Model model){
        ProductDetailEntity productDetailEntity = new ProductDetailEntity();
        model.addAttribute("productDetail", productDetailEntity);
        List<ProductDetailEntity> productDetailEntity1 = productDetailEntityDAO.findAll();
        model.addAttribute("productDetail1", productDetailEntity1);
        return "/admin/productDetail-management";
    }

    @RequestMapping("/form-productDetail")
    public String showFPD(Model model){
        model.addAttribute("productDetail", new ProductDetailEntity());
        // model.addAttribute(null, ProductDetailEntity)
        return "admin/form-productDetail";
    }

    @RequestMapping("/form-productDetailId/{Id}")
    public String showPDId(Model model, @PathVariable("Id") Integer Id){
        List<ProductDetailEntity> productDetailEntity1 = productDetailEntityDAO.findByProductProductId(Id);
        model.addAttribute("productDetail1", productDetailEntity1);
        System.out.println(Id);
        return "admin/productDetail-management";
    }

    
    @RequestMapping("/edit/{productDetailId}")
    public String edit(Model model, @PathVariable("productDetailId") Integer productDetailId){
            ProductDetailEntity productDetailEntity = productDetailEntityDAO.findById(productDetailId).get();
            model.addAttribute("productDetail", productDetailEntity);
            List<ProductDetailEntity> productDetailEntity1 = productDetailEntityDAO.findAll();
            model.addAttribute("productDetail1", productDetailEntity1);
            return "/admin/form-productDetail";
    }


    @RequestMapping("/create")                                                            
    public String create(Model model, @ModelAttribute("productDetail") ProductDetailEntity productDetailEntity,@ModelAttribute("color") ColorEntity ColorEntity, @ModelAttribute("size") SizeEntity sizeEntity,
    BindingResult error){
        productDetailEntity.setColor(ColorEntity);
        productDetailEntity.setSize(sizeEntity);
        productDetailEntityDAO.save(productDetailEntity);

        model.addAttribute("productDetail", new ProductDetailEntity());
        model.addAttribute("color", new ColorEntity());
        model.addAttribute("size", new SizeEntity());
        return "redirect:/productDetail";
    }


    @ModelAttribute("SizeIds")
    public List<String> getAllSizeIds(){
        List<SizeEntity> sizes = sizeEntityDAO.findAll();
        List<String> SizeIds = sizes.stream().map(SizeEntity::getSizeName).collect(Collectors.toList());
        return SizeIds;
    }

    @ModelAttribute("ColorIds")
    public List<String> getAllColorIds(){
        List<ColorEntity> colors = colorEntityDAO.findAll();
        List<String> ColorIds = colors.stream().map(ColorEntity::getColorName).collect(Collectors.toList());
        return ColorIds;
    }

    @RequestMapping("/delete/{productDetailId}")
    public String deletePD(@PathVariable("productDetailId") Integer productDetailId){
        productDetailEntityDAO.deleteById(productDetailId);
        return "redirect:/productDetail";
    }
}
