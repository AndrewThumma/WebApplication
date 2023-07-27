package org.cvschools.WebApplication.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BusinessController {
    
    public Boolean fileUploaded;

    @GetMapping("/403b")
    public String get403b(Model model){
        fileUploaded = false;

        model.addAttribute("fileUploaded", fileUploaded);
        return "403b";
    }

    @PostMapping("/403b")
    public String post403b(Model model){
        fileUploaded = true;


        model.addAttribute("fileUploaded", fileUploaded);
        return "403b";
    }
}
