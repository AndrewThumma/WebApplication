package org.cvschools.WebApplication.services;

import java.util.List;

import org.cvschools.WebApplication.entities.ReportableTerminations;
import org.cvschools.WebApplication.entities.ReportedTerminations;

public interface BuisinessService {

    List<ReportableTerminations> getReportableTerminations();

    List<ReportedTerminations> getReportedTerminations();

    void updateReportableTerminations(List<ReportableTerminations> reportable);

    void clearReportableTerminations();

    void clearActiveStaff();

    void clearImportedData();

    void clearExportData();

    void createReportableTerminations();

    void createUploadTable();

    void updateReportedTerminations();

    void deleteReportedById(String id);
    
}
