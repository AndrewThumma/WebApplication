package org.cvschools.WebApplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    
    @Autowired
    Environment env;

    @GetMapping
    public String getIndex(Model model){ 
        
        String pass = env.getProperty("WebApplicationUser");
        String pass2 = env.getProperty("WebApplicationDB");

        model.addAttribute("pass", pass);
        model.addAttribute("pass2", pass2);

        return "index";
    }
}
