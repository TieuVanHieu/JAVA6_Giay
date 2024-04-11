package com.example.demo.controller.admin;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
public String create(Model model, @ModelAttribute("brand") BrandEntity brandEntity, BindingResult result){
    if (brandEntity.getBrandName() == null || brandEntity.getBrandName().isEmpty()) {
        result.rejectValue("brandName", "error.brandEntity", "Tên thương hiệu không được để trống");
        return "/admin/form-brand"; // Return back to the form for user to correct the error
    }else if (!brandEntity.getBrandName().matches("^[a-zA-Z]+$")) {
        result.rejectValue("brandName", "error.brandEntity", "Tên thương hiệu phải chứa các ký tự chữ cái");
        return "/admin/form-brand"; // Trả về trang form để người dùng sửa lỗi
    }

    // If brandName is not empty, proceed with saving the entity
    brandEntityDAO.save(brandEntity);

    return "redirect:/brand";
}
    

    @RequestMapping("/delete/{brandId}")
    public String delete(@PathVariable("brandId") Integer brandId,Model model, RedirectAttributes redirectAttributes) {
        Optional<BrandEntity> brandOptional = brandEntityDAO.findById(brandId);
        
        if (brandOptional.isPresent()) {
            BrandEntity brandEntity = brandOptional.get();
            
            // Kiểm tra xem có sản phẩm nào liên kết với thương hiệu không
            if (!brandEntity.getProduct().isEmpty()) {
                // Nếu có, không xóa và thông báo lỗi
                // redirectAttributes.addFlashAttribute("error", "Không thể xóa thương hiệu này vì có sản phẩm đang liên kết với nó.");
                model.addAttribute("messageDanger", "Không thể xóa thương hiệu này vì có sản phẩm đang liên kết với nó.");
                return "forward:/brand";
            } else {
                // Nếu không có sản phẩm liên kết, xóa thương hiệu
                brandEntityDAO.deleteById(brandId);
                model.addAttribute("messageSuccess", "Xóa thành công !");
                return "forward:/brand";
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy thương hiệu.");
        }
        
        return "redirect:/brand";
    }

}
