package com.example.demo.restController;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.AccountEntity;
import com.example.demo.model.AuthorityEntity;
import com.example.demo.repository.AccountEntityDAO;
import com.example.demo.repository.AuthorityEntityDAO;
import com.example.demo.repository.RoleEntityDAO;

@RestController
public class AuthRestController {
    @Autowired
    AuthorityEntityDAO authorityEntityDAO;

    @Autowired
    RoleEntityDAO roleEntityDAO;

    @Autowired
    AccountEntityDAO accountEntityDAO;

    @GetMapping("/admin/authorities")
    public Map<String, Object> getAuthorities(){
        Map<String, Object> data = new HashMap<>();
        data.put("authorities", authorityEntityDAO.findAll());
        data.put("roles", roleEntityDAO.findAll());
        data.put("accounts", accountEntityDAO.findAll());
        return  data;
    }

    @PostMapping("/admin/authoritites")
    public AuthorityEntity create(@RequestBody AuthorityEntity authority){
        return authorityEntityDAO.save(authority);
    }

    @DeleteMapping("/admin/authorities/{id}")
    public void delete(@PathVariable("id") Integer id){
        authorityEntityDAO.deleteById(id);
    }

}
