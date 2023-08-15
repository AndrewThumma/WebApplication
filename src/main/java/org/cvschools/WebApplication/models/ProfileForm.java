package org.cvschools.WebApplication.models;

import lombok.Data;

@Data
public class ProfileForm {
    
    private String name;
    private String email;
    private String currentPassword;
    private String newPassword;

}
