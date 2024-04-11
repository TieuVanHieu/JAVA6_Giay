package com.example.demo.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.RoleEntity;
import com.example.demo.repository.RoleEntityDAO;

@Controller
@RequestMapping("/role")

public class AdminRoleMN {
    @Autowired
    RoleEntityDAO roleEntityDAO;

    @RequestMapping("")
    public String showRole(Model model){
        List<RoleEntity> RoleEntity = roleEntityDAO.findAll();
        model.addAttribute("roles", RoleEntity);
        return "/admin/role-management";

    }

}