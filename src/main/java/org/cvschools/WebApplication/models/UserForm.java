package org.cvschools.WebApplication.models;

import java.util.ArrayList;
import java.util.List;

import org.cvschools.WebApplication.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserForm {
    
    public List<User> users;

    public UserForm(){
        users = new ArrayList<>();
    }

    public void adduser(User user){
        users.add(user);
    }
}