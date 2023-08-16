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
import java.util.Optional;

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
       
        //get role
        Role role = roleRepository.findByName(userDto.getRole());

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

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void updatePassword(User user, String newPassword) {
            user.setPassword(passwordEncoder.encode(newPassword));

            userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.ofNullable(userRepository.findById(id).orElse(null));
    }
}

