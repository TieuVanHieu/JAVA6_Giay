package com.example.demo.controller.admin;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.CategoryEntity;

import com.example.demo.repository.CategoryEntityDAO;

@Controller
@RequestMapping("/category")
public class AdminCategoryMN {
    @Autowired
    CategoryEntityDAO categoryEntityDAO;

    @RequestMapping("")
    public String ShowCategory(Model model){
        List<CategoryEntity> categoryEntity = categoryEntityDAO.findAll();
        model.addAttribute("category", categoryEntity);

        return "/admin/Category-management";

    }
    

    @RequestMapping("/form-category")
    public String ShowFC(Model model){
        model.addAttribute("category", new CategoryEntity());

        return "admin/form-category";

    }

    @RequestMapping("/edit/{categoryId}")
    public String edit(Model model, @PathVariable("categoryId") Integer categoryId){
        CategoryEntity categoryEntity = categoryEntityDAO.findById(categoryId).get();
        model.addAttribute("category", categoryEntity);
        List<CategoryEntity> categoryEntity1 = categoryEntityDAO.findAll();
        model.addAttribute("category1", categoryEntity1);

        return "/admin/form-category";

    }

    @RequestMapping("/create")
    public String create(Model model, @ModelAttribute("category") CategoryEntity categoryEntity){
        categoryEntityDAO.save(categoryEntity);

        return "redirect:/category";
        

    }
    @RequestMapping("/delete/{categoryId}")
    public String deleteCategory(@PathVariable("categoryId") Integer categoryId) {
        CategoryEntity category = categoryEntityDAO.findById(categoryId).orElse(null);
    
        if (category != null) {
            categoryEntityDAO.delete(category);
        }
    
        return "redirect:/category"; // Redirect to the category page after deletion
    }

}
