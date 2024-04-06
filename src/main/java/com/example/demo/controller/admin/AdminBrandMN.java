package com.example.demo.controller.admin;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.BrandEntity;
import com.example.demo.repository.BrandEntityDAO;

@Controller
@RequestMapping("/brand")
public class AdminBrandMN {
    @Autowired
    BrandEntityDAO brandEntityDAO;

    @RequestMapping("")
    public String ShowBrand(Model model){
        List<BrandEntity> brandEntity = brandEntityDAO.findAll();
        model.addAttribute("brand", brandEntity);

        return "/admin/Brand-management";

    }
    

    @RequestMapping("/form-brand")
    public String ShowFB(Model model){
        model.addAttribute("brand", new BrandEntity());

        return "admin/form-brand";

    }

    @RequestMapping("/edit/{brandId}")
    public String edit(Model model, @PathVariable("brandId") Integer brandId){
        BrandEntity brandEntity = brandEntityDAO.findById(brandId).get();
        model.addAttribute("brand", brandEntity);
        List<BrandEntity> brandEntity1 = brandEntityDAO.findAll();
        model.addAttribute("brand1", brandEntity1);

        return "/admin/form-brand";

    }

    @RequestMapping("/create")
    public String create(Model model, @ModelAttribute("brand") BrandEntity brandEntity){
        brandEntityDAO.save(brandEntity);

        return "redirect:/brand";

    }

}
