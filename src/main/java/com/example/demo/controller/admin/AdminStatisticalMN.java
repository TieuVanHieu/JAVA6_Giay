package com.example.demo.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.repository.OrderEntityDAO;
import com.example.demo.repository.ProductEntityDAO;

@Controller
@RequestMapping("/statistical")
public class AdminStatisticalMN {
    
    @Autowired
    ProductEntityDAO productEntityDAO;

    @Autowired
    OrderEntityDAO orderEntityDAO;


    @RequestMapping("")
    public String ShowStati(Model model){
        long totalProducts = productEntityDAO.count();
        model.addAttribute("totalProducts", totalProducts);

        long totalOrders = orderEntityDAO.count();
        model.addAttribute("totalOrders", totalOrders);

        // double totalIncome = orderEntityDAO.calculateTotalIncome();
        // model.addAttribute("totalIncome", totalIncome);

        // List<Object[]> topSellingProducts = productEntityDAO.findTopSellingProducts();
        // model.addAttribute("topSellingProducts", topSellingProducts);



        return "/admin/statistical";
    }
}
