package org.cvschools.WebApplication.models;

import java.util.ArrayList;
import java.util.List;

import org.cvschools.WebApplication.entities.ReportableTerminations;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReportableForm {

    public List<ReportableTerminations> reportableTerminations;
    
    public ReportableForm(){
        reportableTerminations = new ArrayList<>();
    }
    
    public void addEmployee(ReportableTerminations r){
        this.reportableTerminations.add(r);
    }
    
}
