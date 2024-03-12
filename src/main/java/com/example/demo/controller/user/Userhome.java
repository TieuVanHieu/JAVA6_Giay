package com.example.demo.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Userhome {
    
    @RequestMapping("/index")
    public String index(){
        return "user/index";
    }
}
