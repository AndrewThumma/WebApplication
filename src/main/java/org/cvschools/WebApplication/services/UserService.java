package org.cvschools.WebApplication.services;

import java.util.List;
import java.util.Optional;

import org.cvschools.WebApplication.entities.Role;
import org.cvschools.WebApplication.entities.User;
import org.cvschools.WebApplication.models.UserDto;

public interface UserService {
    
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<User> findAll();

    void updateUsers(List<User> users);
    
    void updateUser(User user, String roleName);

    void updateUser(Integer id, String name, String email, String roleName);

    String updatePassword(String email, String currentPassword, String newPassword);
    
    void updatePassword(User user, String newPassword);

    void deleteUser(Integer id);

    List<Role> findAllRoles();

    Optional<User> findById(Integer id);

    void setUserRole(Integer id, String roleName);
}
