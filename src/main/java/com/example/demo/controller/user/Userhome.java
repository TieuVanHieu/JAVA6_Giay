package com.example.demo.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Userhome {
	@RequestMapping("/index")
	public String index() {
		return "user/index";
	}

	@RequestMapping("/login")
	public String login() {
		return "user/login";
	}

	@RequestMapping("/product")
	public String product() {
		return "user/product";
	}

	@RequestMapping("/cart")
	public String cart() {
		return "user/cart";
	}

	@RequestMapping("/checkout")
	public String checkout() {
		return "user/checkout";
	}

	@RequestMapping("/category")
	public String category() {
		return "user/category";
	}

	@RequestMapping("/dashboard")
	public String dashboard() {
		return "user/dashboard";
	}
	@RequestMapping("/wishlist")
	public String wishlist() {
		return "user/wishlist";
	}
	@RequestMapping("/category-boxed")
	public String categoryBoxed() {
		return "user/category-boxed";
	}
	
}
