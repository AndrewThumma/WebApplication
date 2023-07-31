package org.cvschools.WebApplication.services;

import java.util.List;

import org.cvschools.WebApplication.entities.ReportableTerminations;
import org.cvschools.WebApplication.repositories.ReportableTerminationsRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReportableTerminationsServiceImpl implements ReportableTerminationsService{

    private final ReportableTerminationsRepository repo;
    
    @Override
    public List<ReportableTerminations> getReportableTerminations() {
        return repo.findAll();
    }

    @Override
    public void updateReportableTerminations(List<ReportableTerminations> reportable) {
        repo.saveAll(reportable);
    }

    @Override
    public void clearReportableTerminations() {
        repo.deleteAll();
    }

    @Override
    public void createReportableTerminations() {
        // mthod to run stored procedure 

        
        throw new UnsupportedOperationException("Unimplemented method 'createReportableTerminations'");
    }
    
}
