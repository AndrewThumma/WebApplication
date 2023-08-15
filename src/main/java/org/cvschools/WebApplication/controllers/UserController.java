package org.cvschools.WebApplication.controllers;

import org.cvschools.WebApplication.entities.User;
import org.cvschools.WebApplication.models.ProfileForm;
import org.cvschools.WebApplication.models.UserDto;
import org.cvschools.WebApplication.models.UserForm;
import org.cvschools.WebApplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class UserController {
    
    @Autowired
    UserService userService;


    @GetMapping("/users")
    public String getUsers(Model model){
        //create form object with list of users
        UserForm form = new UserForm();
        form.setUsers(userService.findAll());

        UserDto user = new UserDto();
        
        model.addAttribute("user", user);
        model.addAttribute("form", form);
        
        return "users";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(Model model, @RequestParam Integer id){
        userService.deleteUser(id);

        return "users";
    }

    @PostMapping("/createUser")
    public String createUser(Model model, @ModelAttribute UserDto userDto, @ModelAttribute UserForm form){
        userService.saveUser(userDto);

        form.setUsers(userService.findAll());
        
        model.addAttribute("form", form);
        model.addAttribute("userDto", new UserDto());
        model.addAttribute("message", "Success");

        return "users";
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
            Model model,
            @ModelAttribute UserForm form) {
  
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

        form.setUsers(userService.findAll());

        model.addAttribute("form", form);

        return "users";
    }
}
