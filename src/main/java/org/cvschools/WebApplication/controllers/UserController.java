package org.cvschools.WebApplication.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.cvschools.WebApplication.Exceptions.NotFoundException;
import org.cvschools.WebApplication.entities.Role;
import org.cvschools.WebApplication.entities.User;
import org.cvschools.WebApplication.models.UserDto;
import org.cvschools.WebApplication.models.UserForm;
import org.cvschools.WebApplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

        List<Role> roles = new ArrayList<>();
        roles.addAll(userService.findAllRoles());
        
        model.addAttribute("message", null);
        model.addAttribute("roles", roles);
        model.addAttribute("user", user);
        model.addAttribute("form", form);
        
        return "users";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(Model model, @PathVariable Integer id, @ModelAttribute UserForm form){
        userService.deleteUser(id);

        form.setUsers(userService.findAll());

        UserDto user = new UserDto();
        List<Role> roles = new ArrayList<>();
        roles.addAll(userService.findAllRoles());

        model.addAttribute("message", "User Deleted!");
        model.addAttribute("roles", roles);
        model.addAttribute("user", user);
        model.addAttribute("form", form);

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
    @PostMapping("/users")
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
            form.setUsers(userService.findAll());

            model.addAttribute("message", result.getAllErrors().toString());
            model.addAttribute("form", form);
            model.addAttribute("user", userDto);
            return "users";
        }

        userService.saveUser(userDto);

        form.setUsers(userService.findAll());

        List<Role> roles = new ArrayList<>();
        roles.addAll(userService.findAllRoles());

        model.addAttribute("user", new UserDto());
        model.addAttribute("roles", roles);
        model.addAttribute("message", "User Created Successfully!");
        model.addAttribute("form", form);

        return "users";
    }

    @GetMapping("/resetPassword/{id}")
    public String resetPassword(Model model, @PathVariable Integer id, @RequestParam String newPassword, 
                                @ModelAttribute UserForm form,
                                @ModelAttribute List<Role> roles){
        User user = userService.findById(id).orElseThrow(NotFoundException::new);

        userService.updatePassword(user, newPassword);

        model.addAttribute("user", new UserDto());
        model.addAttribute("roles", roles);
        model.addAttribute("message", "User Created Successfully!");
        model.addAttribute("form", form);

        return "users";
    }
}
