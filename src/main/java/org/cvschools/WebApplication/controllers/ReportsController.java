package org.cvschools.WebApplication.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReportsController {
    

    @GetMapping
    public String getReports(Model model){

        return "Reports";
    }

    @GetMapping("/DataVisualization/{id}")
    public String getVisualization(Model model, @RequestParam Integer id){


        return "DataVisualization";
    }
}
