package org.cvschools.WebApplication.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ReportableTerminations", schema = "403b")
public class ReportableTerminations {
    
    @Id
    @Column(length = 255, columnDefinition = "nvarchar(255)", nullable = false, name = "staffId")
    private String staffId;

    @Column(length = 255, columnDefinition = "nvarchar(255)", nullable = true, name = "ssn")
    private String ssn;

    @Column(length = 255, columnDefinition = "nvarchar(255)", nullable = true, name = "lastName")
    private String lastName;

    @Column(length = 255, columnDefinition = "nvarchar(255)", nullable = true, name = "firstName")
    private String firstName;

    @Column(length = 255, columnDefinition = "nvarchar(255)", nullable = true, name = "middleInitial")
    private String middleInitial;

    @Column(length = 255, columnDefinition = "nvarchar(255)", nullable = true, name = "birthDate")
    private String birthDate;

    @Column(length = 255, columnDefinition = "nvarchar(255)", nullable = true, name = "hireDate")
    private String hireDate;

    @Column(length = 255, columnDefinition = "nvarchar(255)", nullable = true, name = "terminationDate")
    private String terminationDate;

    @Column(length = 255, columnDefinition = "nvarchar(255)", nullable = true, name = "email")
    private String email;

    @Column(length = 255, columnDefinition = "nvarchar(255)", nullable = true, name = "phoneNumber")
    private String phoneNumber;

    @Column(length = 255, columnDefinition = "nvarchar(255)", nullable = true, name = "address1")
    private String address1;

    @Column(length = 255, columnDefinition = "nvarchar(255)", nullable = true, name = "address2")
    private String address2;

    @Column(length = 255, columnDefinition = "nvarchar(255)", nullable = true, name = "city")
    private String city;

    @Column(length = 255, columnDefinition = "nvarchar(255)", nullable = true, name = "state")
    private String state;

    @Column(length = 255, columnDefinition = "nvarchar(255)", nullable = true, name = "zip")
    private String zip;
}
