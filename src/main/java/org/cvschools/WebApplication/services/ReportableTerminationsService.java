package org.cvschools.WebApplication.services;

import java.util.List;

import org.cvschools.WebApplication.entities.ReportableTerminations;

public interface ReportableTerminationsService {

    List<ReportableTerminations> getReportableTerminations();

    void updateReportableTerminations(List<ReportableTerminations> reportable);

    void clearReportableTerminations();

    void createReportableTerminations();
    
}
