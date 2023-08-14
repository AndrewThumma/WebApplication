package org.cvschools.WebApplication.controllers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.cvschools.WebApplication.ExceptionHandlers.AuthenticationFailureException;
import org.cvschools.WebApplication.services.PowerBi.DefaultPowerBiConnectionFactory;
import org.cvschools.WebApplication.services.PowerBi.Office365Authenticator;
import org.cvschools.WebApplication.services.PowerBi.PowerBiConnection;
import org.cvschools.WebApplication.services.PowerBi.PowerBiConnectionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReportsController {
    
    private String clientId;
    private String tenant;
    private String user;
    private String pass;

    private String embedUrl;


    @GetMapping
    public String getReports(Model model){

        return "Reports";
    }

    @GetMapping("/DataVisualization/{id}")
    public String getVisualization(Model model, @RequestParam Integer id) throws AuthenticationFailureException{
        //get access token
        ExecutorService executor = Executors.newFixedThreadPool(10);
        Office365Authenticator auth = new Office365Authenticator(clientId, tenant, user, pass, executor);
        String accessToken = auth.authenticate();

        //get embedurl from powerbi rest api GET https://api.powerbi.com/v1.0/myorg/groups/{groupId}/reports/{reportId}

        
        model.addAttribute("accessToken", accessToken);
        model.addAttribute("embedUrl", embedUrl);
        
        return "DataVisualization";
    }
}
