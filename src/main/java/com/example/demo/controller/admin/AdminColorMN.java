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

import com.example.demo.model.ColorEntity;
import com.example.demo.model.SizeEntity;
import com.example.demo.repository.ColorEntityDAO;

@Controller
@RequestMapping("/color")
public class AdminColorMN {
    @Autowired
    ColorEntityDAO colorEntityDAO;


    @RequestMapping("")
    public String ShowColor(Model model){
        List<ColorEntity> ColorEntity = colorEntityDAO.findAll();
        model.addAttribute("color", ColorEntity);
        return "/admin/Color-management";
    }


    @RequestMapping("/form-color")
    public String ShowFC(Model model){
        model.addAttribute("color", new ColorEntity());
        return "admin/form-color";
    }

    @RequestMapping("edit/{colorId}")
    public String edit(Model model, @PathVariable("colorId") Integer colorId){
        ColorEntity colorEntity = colorEntityDAO.findById(colorId).get();
        model.addAttribute("color", colorEntity);
        List<ColorEntity> colorEntity1 = colorEntityDAO.findAll();
        model.addAttribute("color1", colorEntity1);
        return "/admin/form-color";
    }

    @RequestMapping("/create")
    public String create(Model model, @ModelAttribute("color") ColorEntity colorEntity, BindingResult result) {
        if (colorEntity.getColorName() == null || colorEntity.getColorName().isEmpty()) {
            result.rejectValue("colorName", "error.colorEntity", "Tên màu không được để trống");
            return "/admin/form-color"; // Trả về trang form để người dùng sửa lỗi
        } else if (!colorEntity.getColorName().matches("^[a-zA-Z]+$")) {
            result.rejectValue("colorName", "error.colorEntity", "Tên màu phải chứa các ký tự chữ cái");
            return "/admin/form-color"; // Trả về trang form để người dùng sửa lỗi
        }
    
        // Check for Duplicate Color Name
        ColorEntity existingColor = colorEntityDAO.findByColorName(colorEntity.getColorName());
        if (existingColor != null) {
            result.rejectValue("colorName", "error.colorEntity", "Tên màu đã tồn tại, vui lòng chọn tên khác");
            return "/admin/form-color"; // Trả về trang form để người dùng sửa lỗi
        }
    
        // Nếu không có lỗi (tên màu không trùng) tiến hành lưu entity
        colorEntityDAO.save(colorEntity);
    
        return "redirect:/color";
    }
    
    @RequestMapping("/delete/{colorId}")
    public String delete(@PathVariable("colorId") Integer colorId,Model model, RedirectAttributes redirectAttributes) {
        Optional<ColorEntity> colorOptional = colorEntityDAO.findById(colorId);
        
        if (colorOptional.isPresent()) {
            ColorEntity colorEntity = colorOptional.get();
            
            // Kiểm tra xem có sản phẩm nào liên kết với hãng không
            if (!colorEntity.getProductDetail().isEmpty()) {
                // Nếu có, không xóa và thông báo lỗi
                // redirectAttributes.addFlashAttribute("error", "Không thể xóa hãng này vì có sản phẩm đang liên kết với nó.");
                model.addAttribute("messageDanger", "Không thể xóa màu này vì có sản phẩm đang liên kết với nó.");
                return "forward:/brand";
            } else {
                // Nếu không có sản phẩm liên kết, xóa hãng
                colorEntityDAO.deleteById(colorId);
                model.addAttribute("messageSuccess", "Xóa thành công !");
                return "forward:/brand";
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy hãng.");
        }
        
        return "redirect:/brand";
    }
}
