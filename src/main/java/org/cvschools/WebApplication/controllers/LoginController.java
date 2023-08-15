package org.cvschools.WebApplication.controllers;


import java.util.Map;

import jakarta.validation.Valid;

import org.cvschools.WebApplication.entities.User;
import org.cvschools.WebApplication.models.UserDto;
import org.cvschools.WebApplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * controller to handle login mapping
 * 
 * calls UserService to connect to API for getting user inforamtion
 */

@Controller
public class LoginController {
    
    @Autowired
    private UserService userService;


    //mapping for showing the login page
    @RequestMapping("/login")
    public String getLogin(Map<String, Object> model) {
        return "login";
    }

    //get mapping to display user registration page
    @GetMapping("/register")
    public String registrationForm(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    //post mapping to create a new user
    @PostMapping("/register")
    public String registration(
            @Valid @ModelAttribute("user") UserDto userDto,
            BindingResult result,
            Model model) {
  
        //attempt to find existing user with supplied username/email
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        //check for existing user and display error if found
        if (existingUser != null)
            result.rejectValue("email", null,
                    "User already registered !!!");

        //check for other errors and display as needed
        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/registration?success";
    }

}

