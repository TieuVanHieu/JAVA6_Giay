package com.example.demo.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.SizeEntity;
import com.example.demo.repository.SizeEntityDAO;

@Controller
@RequestMapping("/size")
public class AdminSizeMN {
    @Autowired
    SizeEntityDAO sizeEntityDAO;


    @RequestMapping("")
    public String ShowSize(Model model){
        List<SizeEntity> SizeEntity = sizeEntityDAO.findAll();
        model.addAttribute("size", SizeEntity);
        return "/admin/Size-management";
    }

    @RequestMapping("/form-size")
    public String showFS(Model model){
        model.addAttribute("size", new SizeEntity());
        return "admin/form-size";
    }

    @RequestMapping("/edit/{sizeId}")
    public String edit(Model model, @PathVariable("sizeId") Integer sizeId){
        SizeEntity SizeEntity = sizeEntityDAO.findById(sizeId).get();
        model.addAttribute("size", SizeEntity);
        List<SizeEntity> SizeEntity1 = sizeEntityDAO.findAll();
        model.addAttribute("size1", SizeEntity1);
        return "/admin/form-size";
    }

    @RequestMapping("/create")
    public String create(Model model, @ModelAttribute("size") SizeEntity sizeEntity){
        sizeEntityDAO.save(sizeEntity);
        return "redirect:/size";
    }

    @RequestMapping("/delete/{sizeId}")
    public String delete(@PathVariable("sizeId") Integer sizeId, RedirectAttributes redirectAttributes) {
        Optional<SizeEntity> sizeOptional = sizeEntityDAO.findById(sizeId);
        
        if (sizeOptional.isPresent()) {
            SizeEntity sizeEntity = sizeOptional.get();
            
            // Kiểm tra xem có sản phẩm nào liên kết với kích thước không
            if (!sizeEntity.getProductDetail().isEmpty()) {
                // Nếu có, không xóa và thông báo lỗi
                redirectAttributes.addFlashAttribute("error", "Không thể xóa kích thước này vì có sản phẩm đang liên kết với nó.");
            } else {
                // Nếu không có sản phẩm liên kết, xóa kích thước
                sizeEntityDAO.deleteById(sizeId);
                redirectAttributes.addFlashAttribute("success", "Kích thước đã được xóa thành công.");
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy kích thước.");
        }
        
        return "redirect:/size";
    }
}
