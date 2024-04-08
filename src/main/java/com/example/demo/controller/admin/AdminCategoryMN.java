package com.example.demo.controller.admin;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String delete(@PathVariable("categoryId") Integer categoryId,Model model, RedirectAttributes redirectAttributes) {
        Optional<CategoryEntity> categoryOptional = categoryEntityDAO.findById(categoryId);
        
        if (categoryOptional.isPresent()) {
            CategoryEntity categoryEntity = categoryOptional.get();
            
            // Kiểm tra xem có sản phẩm nào liên kết với hãng không
            if (!categoryEntity.getProduct().isEmpty()) {
                // Nếu có, không xóa và thông báo lỗi
                // redirectAttributes.addFlashAttribute("error", "Không thể xóa hãng này vì có sản phẩm đang liên kết với nó.");
                model.addAttribute("messageDanger", "Không thể xóa hãng này vì có sản phẩm đang liên kết với nó.");
                return "forward:/brand";
            } else {
                // Nếu không có sản phẩm liên kết, xóa hãng
                categoryEntityDAO.deleteById(categoryId);
                model.addAttribute("messageSuccess", "Xóa thành công !");
                return "forward:/brand";
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy hãng.");
        }
        
        return "redirect:/brand";
    }

}
