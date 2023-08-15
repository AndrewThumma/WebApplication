package org.cvschools.WebApplication.bootstrap;

import java.util.Arrays;

import org.cvschools.WebApplication.entities.Role;
import org.cvschools.WebApplication.entities.User;
import org.cvschools.WebApplication.repositories.RoleRepository;
import org.cvschools.WebApplication.repositories.UserRepository;
import org.cvschools.WebApplication.utilities.TbConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminUser implements CommandLineRunner{

    @Autowired
    UserRepository userRepo;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        
        Role role1 = roleRepository.findByName(TbConstants.Roles.BUSINESS);
        if (role1 == null){
            roleRepository.save(new Role(TbConstants.Roles.BUSINESS));
        }

        Role role2 = roleRepository.findByName(TbConstants.Roles.USER);
        if (role2 == null){
            roleRepository.save(new Role(TbConstants.Roles.USER));
        }
        
        User existingUser = userRepo.findByEmail("ajthumma@cvschools.org");
        
        if(existingUser != null) {
            
        }else {
            Role role = roleRepository.findByName(TbConstants.Roles.ADMIN);
            
            if (role == null) {
                role = roleRepository.save(new Role(TbConstants.Roles.ADMIN));
            }
                        
            User user = new User("Andrew Thumma", "ajthumma@cvschools.org", passwordEncoder.encode("J@smine56"), Arrays.asList(role));

            userRepo.save(user);
        }        
        
    }
    
}
