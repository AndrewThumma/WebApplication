package org.cvschools.WebApplication.services;


import org.cvschools.WebApplication.entities.User;
import org.cvschools.WebApplication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

//import com.snhu.Flights.models.User;
//import com.snhu.Flights.repositories.UserRepository;

import java.util.stream.Collectors;

/*
 * service class used to create user details for spring security
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    //create user details
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        
        //original working method with local datastore
        User user = userRepository.findByEmail(usernameOrEmail);       

        if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getEmail()
                    , user.getPassword(),
                    user.getRoles().stream()
                            .map((role) -> new SimpleGrantedAuthority(role.getName()))
                            .collect(Collectors.toList()));
        } else {
            throw new UsernameNotFoundException("Invalid email or password");
        }

    }
}
