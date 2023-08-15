package org.cvschools.WebApplication.controllers;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * controller to handle login mapping
 * 
 * calls UserService to connect to API for getting user inforamtion
 */

@Controller
public class LoginController {
    
    //mapping for showing the login page
    @RequestMapping("/login")
    public String getLogin(Map<String, Object> model) {
        return "login";
    }



}

