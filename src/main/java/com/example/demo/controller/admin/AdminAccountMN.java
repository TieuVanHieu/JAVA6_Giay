package com.example.demo.controller.admin;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import com.example.demo.model.AccountEntity;
import com.example.demo.model.AuthorityEntity;
// import com.example.demo.model.ShippingInfoEntity;
import com.example.demo.repository.AccountEntityDAO;
import com.example.demo.repository.AuthorityEntityDAO;
import com.example.demo.service.AccountService;



@Controller
@RequestMapping("/account")
public class AdminAccountMN {
    @Autowired
    AccountEntityDAO AccountEntityDAO;

    @Autowired
    AccountService AccountService;

    @RequestMapping("")
    public String ShowAccount(Model model) {
        List<AccountEntity> AccountEntity = AccountEntityDAO.findAll();
        model.addAttribute("account", AccountEntity);;
        return "admin/account-management";
    }

    // @GetMapping("/authorities")
    // public String Autho(){
    //     return "admin/role-management";
    // }
    // @RequestMapping("/form-account")
    // public String ShowFA(Model model) {
    //     model.addAttribute("account", new AccountEntity());
    //     return "admin/form-account"; 
    // }

    // @RequestMapping("/edit/{id}")
    // public String edit(@PathVariable("id") String id, Model model){
    //     AccountEntity accountEntity = AccountService.findById(id)
    //     .orElseThrow(() -> new IllegalArgumentException("Username không tồn tại: " + id));
    //     model.addAttribute("account", accountEntity);
    //     return "/admin/form-account";
    // }

    // @PostMapping("/edit/{username}")
    // public String update(@PathVariable("userName") String userName, @ModelAttribute("account") AccountEntity account,Model model){
    //     AccountEntity u = AccountEntityDAO.findById(account.getUserName()).orElseThrow();
    //     AccountEntityDAO.save(u);
    //     return "redirect:/account";
    // }

    // @RequestMapping("/detele/{username}")
    // public String delete(@PathVariable("userName") String userName, @ModelAttribute("account") AccountEntity account,Model model){
    //    AccountEntity u = AccountEntityDAO.findById(account.getUserName()).orElseThrow();
    //     return "redirect:/account";
    // }
}
