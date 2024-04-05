package com.example.demo.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.OrderDetailEntity;
import com.example.demo.model.OrderEntity;
import com.example.demo.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	OrderService orderService;

    @RequestMapping("/order/list")
	public String dashboard(Model model, HttpServletRequest request) {
		String username = request.getRemoteUser();
		model.addAttribute("order", orderService.findByUsername(username));
		return "user/orderList";
	}

	@RequestMapping("/order/account")
	public String orderAccount() {
		return "user/orderAccount";
	}

	@RequestMapping("/order/detail/{id}")
	public String orderDetail(@PathVariable("id") Integer id,Model model) {
		double totalPrice = 0;
		model.addAttribute("order", orderService.findById(id));
		for (OrderDetailEntity detail : orderService.findById(id).getOrderDetail()) {
			totalPrice+=detail.getProductDetail().getProduct().getProductPrice()*detail.getQuantity();
		}
		model.addAttribute("totalPrice", totalPrice);
		return "user/orderDetail";
	}
}
