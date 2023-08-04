package org.cvschools.WebApplication.models;

import java.util.ArrayList;
import java.util.List;

import org.cvschools.WebApplication.entities.ReportedTerminations;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReportedForm {

    public List<ReportedTerminations> reported;
    
    public ReportedForm(){
        reported = new ArrayList<>();
    }
    
    public void addEmployee(ReportedTerminations r){
        reported.add(r);
    }
    
}
