package org.cvschools.WebApplication.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImportedEmployeeDTO {
    
    private String staffId;
    private String ssn;
    private String lastName;
    private String firstName;
    private String middleInitial;
    private String birthDate;
    private String hireDate;
    private String terminationDate;
    private String email;
    private String phoneNumber;
    private String address1;
    private String address2;
    private String city;
    private String State;
    private String zip;
}
