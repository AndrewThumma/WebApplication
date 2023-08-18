package org.cvschools.WebApplication.controllers;

import org.cvschools.WebApplication.entities.User;
import org.cvschools.WebApplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {
    
    @Autowired
    UserService userService;

    @GetMapping("/profile/{email}")
    public String getUserToEdit(Model model, @PathVariable String email){
        User user = userService.findUserByEmail(email);

        model.addAttribute("message", "");
        model.addAttribute("user", user);

        return "profile";
    }

    @PostMapping("/profile")
    public String updateUser(Model model, @RequestParam String email, @RequestParam String currentPassword, 
                                @RequestParam String newPassword){

        String results = userService.updatePassword(email, currentPassword, newPassword);
        
        User user = userService.findUserByEmail(email);
        
        model.addAttribute("user", user);
        model.addAttribute("message", results);
        
        return "profile";
    }
}
