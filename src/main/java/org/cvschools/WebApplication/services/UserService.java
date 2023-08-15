package org.cvschools.WebApplication.services;

import java.util.List;

import org.cvschools.WebApplication.entities.User;
import org.cvschools.WebApplication.models.UserDto;

public interface UserService {
    
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<User> findAll();

    void updateUsers(List<User> users);

    String updatePassword(User user, String currentPassword, String newPassword);

    void deleteUser(Integer id);
}
