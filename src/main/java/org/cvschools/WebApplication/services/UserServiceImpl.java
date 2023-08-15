package org.cvschools.WebApplication.services;


import org.cvschools.WebApplication.entities.Role;
import org.cvschools.WebApplication.entities.User;
import org.cvschools.WebApplication.models.UserDto;
import org.cvschools.WebApplication.repositories.RoleRepository;
import org.cvschools.WebApplication.repositories.UserRepository;
import org.cvschools.WebApplication.utilities.TbConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/*
 * service implementaion for managing users
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserDto userDto) {
        
        //get role from roles database or create it if not exists
        Role role = roleRepository.findByName(TbConstants.Roles.USER);
        if (role == null)
            role = roleRepository.save(new Role(TbConstants.Roles.USER));

        //create new user
        User user = new User(userDto.getName(), userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()),
                Arrays.asList(role));
        
        //save user to database
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void updateUsers(List<User> users) {
        userRepository.saveAll(users);
    }

    @Override
    public String updatePassword(User user, String currentPassword, String newPassword) {
        if (passwordEncoder.encode(currentPassword) == user.getPassword()){

            user.setPassword(passwordEncoder.encode(newPassword));

            userRepository.save(user);

            return "Update Successful";
        }else {
            return "Current Password incorrect";
        }
    }
}

