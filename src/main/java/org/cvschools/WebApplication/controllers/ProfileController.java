package org.cvschools.WebApplication.controllers;

import org.cvschools.WebApplication.models.ProfileForm;
import org.cvschools.WebApplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfileController {
    
    @Autowired
    UserService userService;

    @GetMapping("/profile")
    public String getProfile(Model model){
        //get user information and put in profile form
        ProfileForm form = new ProfileForm();

        model.addAttribute("form", form);

        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(Model model, @ModelAttribute ProfileForm form){

        userService.updatePassword(userService.findUserByEmail(form.getEmail()), form.getCurrentPassword(), form.getNewPassword());

        model.addAttribute("form", form);

        return "profile";
    }
}
