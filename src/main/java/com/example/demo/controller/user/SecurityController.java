package com.example.demo.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.AccountEntity;
import com.example.demo.service.UserService;
import com.example.demo.servicelmpl.AccountServicelmpl;

@Controller
public class SecurityController {
    @Autowired
    AccountServicelmpl accountServicelmpl;
    @Autowired
    UserService userService;

    @RequestMapping("/security/login/form")
    public String loginForm(Model model) {
        model.addAttribute("messageWarning", "Vui long dang nhap !");
        // AccountEntity account = new AccountEntity();
        // model.addAttribute("account", account);
        return "user/login";
    }

    @RequestMapping("/security/login/success")
    public String loginSuccess(Model model) {
        model.addAttribute("messageSuccess", "Đăng nhập thành công !");
        return "user/login";
    }

    @RequestMapping("/security/login/error")
    public String loginError(Model model) {
        model.addAttribute("messageDanger", "Đăng nhập thất bại !");
        return "user/login";
    }

    @RequestMapping("/security/unauthoried")
    public String unauthoried(Model model) {
        model.addAttribute("messageWarning", "Bạn không có quyền truy cập vào trang này !");
        return "user/login";
    }

    @RequestMapping("/security/logoff/success")
    public String logoffSuccess(Model model) {
        model.addAttribute("messageSuccess", "Đăng xuất thành công !");
        return "user/login";
    }

    @RequestMapping("/oauth2/login/success")
    public String success(OAuth2AuthenticationToken oauth2){
        userService.loginFormOAuth2(oauth2);
        return "forward:/security/login/success";
    }
}
