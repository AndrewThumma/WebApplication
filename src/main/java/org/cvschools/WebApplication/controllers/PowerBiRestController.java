package org.cvschools.WebApplication.controllers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.cvschools.WebApplication.services.PowerBi.DefaultPowerBiConnectionFactory;
import org.cvschools.WebApplication.services.PowerBi.Office365Authenticator;
import org.cvschools.WebApplication.services.PowerBi.PowerBiConnection;
import org.cvschools.WebApplication.services.PowerBi.PowerBiConnectionFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PowerBiRestController {
    
    private String clientId;
    private String tenant;
    private String user;
    private String pass;

    @GetMapping("/powerBi/authenticate")
    public void authenticate(){
        ExecutorService executor = Executors.newFixedThreadPool(10);

        Office365Authenticator auth = new Office365Authenticator(clientId, tenant, user, pass, executor);

        PowerBiConnectionFactory factory = new DefaultPowerBiConnectionFactory(auth, executor);

        PowerBiConnection connection = factory.create();

    }
}
