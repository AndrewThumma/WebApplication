package org.cvschools.WebApplication.services;

import org.cvschools.WebApplication.entities.User;
import org.cvschools.WebApplication.models.UserDto;

public interface UserService {
    
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);
}
