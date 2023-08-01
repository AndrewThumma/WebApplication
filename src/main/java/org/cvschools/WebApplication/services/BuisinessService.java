package org.cvschools.WebApplication.services;

import java.util.List;

import org.cvschools.WebApplication.entities.ReportableTerminations;

public interface BuisinessService {

    List<ReportableTerminations> getReportableTerminations();

    void updateReportableTerminations(List<ReportableTerminations> reportable);

    void clearReportableTerminations();

    void clearActiveStaff();

    void clearImportedData();

    void clearExportData();

    void createReportableTerminations();

    void createUploadTable();

    void updateReportedTerminations();
    
}
