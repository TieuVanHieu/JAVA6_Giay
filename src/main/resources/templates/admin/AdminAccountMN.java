package com.example.demo.controller.admin;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import com.example.demo.model.AccountEntity;
// import com.example.demo.model.ShippingInfoEntity;
import com.example.demo.repository.AccountEntityDAO;



@Controller
@RequestMapping("/account")
public class AdminAccountMN {
    @Autowired
    AccountEntityDAO AccountEntityDAO;

    @RequestMapping("")
    public String ShowAccount(Model model) {
        List<AccountEntity> AccountEntity = AccountEntityDAO.findAll();
        model.addAttribute("user", AccountEntity);;
        return "admin/account/account-management";
    }

    @RequestMapping("/form-account")
    public String ShowFA(Model model) {
        model.addAttribute("account", new AccountEntity());
        return "/admin/account/form-account"; 
    }

    // @RequestMapping("/edit/{id}")
    // public String edit(Model model, @PathVariable("id") Integer id){
    //     AccountEntity accountEntity = AccountEntityDAO.findById(id).get();
    //     AccountEntity userName = AccountEntityDAO.getByuserName(accountEntity.getUserName());
       
    //     model.addAttribute("account", userEntity);
    //     return "/admin/form-account";
    // }

    // @RequestMapping("/update")
    // public String update(@ModelAttribute("account") AccountEntity account,Model model){
    //     AccountEntity u = AccountEntityDAO.findById(account.getUserId()).orElseThrow();
    //     u.setUserRole(account.getUserRole());
    //     AccountEntityDAO.save(u);
    //     return "redirect:/account";
    // }

    // @RequestMapping("/detele/{id}")
    // public String delete(@PathVariable("id") Integer id){
    //     AccountEntityDAO.deleteById(id);
    //     return "redirect:/account";
    // }
}
