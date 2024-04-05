package com.example.demo.controller.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.AccountEntity;
import com.example.demo.model.AuthorityEntity;
import com.example.demo.model.ProductEntity;
import com.example.demo.repository.AccountEntityDAO;
import com.example.demo.repository.AuthorityEntityDAO;
import com.example.demo.repository.RoleEntityDAO;
import com.example.demo.service.ProductService;

@Controller
public class UserHomeController {
	@Autowired
	ProductService productService;
	@Autowired
	AccountEntityDAO accountEntityDAO;
	@Autowired
	AuthorityEntityDAO authorityEntityDAO;
	@Autowired
	RoleEntityDAO roleEntityDAO;

	@RequestMapping("/index")
	public String index(@RequestParam("p") Optional<Integer> p,Model model) {
		Pageable pageable = PageRequest.of(p.orElse(0),10);
		Page<ProductEntity> page = productService.findAll(pageable);
		model.addAttribute("items", page);
		return "user/index";
	}

	@GetMapping("/signUp/form")
	public String login(Model model) {
		AccountEntity account = new AccountEntity();
        model.addAttribute("account", account);
		return "user/signUp";
	}

	@PostMapping("/signUp")
	public String signup(Model model,@Validated @ModelAttribute("account") AccountEntity account,Errors error,@ModelAttribute("confirmPass") String cfp) {
		if (error.hasErrors()) {
			model.addAttribute("messageDanger", "Đăng ký thất bại !");
			return "user/signup";
		}else if (cfp.isEmpty()) {
			model.addAttribute("ercfPass", "Chưa xác nhận mật khẩu ! ");
			return "user/signup";
		}else if (!account.getPassWord().equals(cfp)) {
			model.addAttribute("ercfPass", "Mật khẩu xác nhận không khớp ! ");
			return "user/signup";
		}
		accountEntityDAO.save(account);
		AuthorityEntity authorityEntity = new AuthorityEntity();
		authorityEntity.setAccount(account);
		authorityEntity.setRole(roleEntityDAO.findById("CUST").get());
		authorityEntityDAO.save(authorityEntity);
		model.addAttribute("messageSuccess", "Đăng ký thành công ! ");
        return "user/login";
    }

	@RequestMapping("/cart")
	public String cart() {
		return "user/cart";
	}

	@RequestMapping("/order/checkout")
	public String checkout() {
		return "user/checkout";
	}


	
	@RequestMapping("/wishlist")
	public String wishlist() {
		return "user/wishlist";
	}
	
}
