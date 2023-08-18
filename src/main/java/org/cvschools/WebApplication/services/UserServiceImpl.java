package org.cvschools.WebApplication.services;


import org.cvschools.WebApplication.Exceptions.NotFoundException;
import org.cvschools.WebApplication.entities.Role;
import org.cvschools.WebApplication.entities.User;
import org.cvschools.WebApplication.models.UserDto;
import org.cvschools.WebApplication.repositories.RoleRepository;
import org.cvschools.WebApplication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public String updatePassword(String email, String currentPassword, String newPassword) {
        User user = userRepository.findByEmail(email);
        
        Boolean check = passwordEncoder.matches(currentPassword, user.getPassword());

        if (check == false) {        
            return "Current password incorrect!";
        }

        user.setPassword(passwordEncoder.encode(newPassword));

        userRepository.save(user);
                
        return "Password updated successfully!";
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

    @Override
    public void setUserRole(Integer id, String roleName) {
        Role role = roleRepository.findByName(roleName);
        User user = userRepository.findById(id).orElseThrow(NotFoundException::new);

        List<Role> roles = new ArrayList<>();
        roles.add(role);

        user.setRoles(roles);

        userRepository.save(user);
    }

    @Override
    public void updateUser(User user, String roleName) {
        Role role = roleRepository.findByName(roleName);
        List<Role> roles = new ArrayList<>();
        roles.add(role);

        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public void updateUser(Integer id, String name, String email, String roleName) {
        User user = userRepository.findById(id).orElseThrow(NotFoundException::new);
        Role role = roleRepository.findByName(roleName);
        List<Role> roles = new ArrayList<>();
        roles.add(role);

        user.setName(name);
        user.setEmail(email);        
        user.setRoles(roles);

        userRepository.save(user);
    }
}

