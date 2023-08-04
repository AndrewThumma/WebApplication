package org.cvschools.WebApplication.services;

import java.util.List;

import javax.sql.DataSource;

import org.cvschools.WebApplication.entities.ReportableTerminations;
import org.cvschools.WebApplication.entities.ReportedTerminations;
import org.cvschools.WebApplication.repositories.ActiveStaffRepository;
import org.cvschools.WebApplication.repositories.ExportEmployeeRepository;
import org.cvschools.WebApplication.repositories.ImportedEmployeeRepository;
import org.cvschools.WebApplication.repositories.ReportableTerminationsRepository;
import org.cvschools.WebApplication.repositories.ReportedTerminationsRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import com.zaxxer.hikari.pool.HikariPool;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BuisinessServiceImpl implements BuisinessService{
    
    //repositories
    private final ReportableTerminationsRepository reportableRepo;    
    private final ExportEmployeeRepository exportRepo;
    private final ImportedEmployeeRepository importedRepo;
    private final ActiveStaffRepository activeRepo;
    private final ReportedTerminationsRepository reportedRepo;

    //values for datasource
    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    @Value("${spring.datasource.url}")
    private String url;
    
    @Value("${spring.datasource.username}")
    private String username;
    
    @Value("${spring.datasource.password}")
    private String password;
    

    @Override
    public List<ReportableTerminations> getReportableTerminations() {
        return reportableRepo.findAll(Sort.by(Sort.Direction.ASC, "staffId"));
    }

    @Override
    public void updateReportableTerminations(List<ReportableTerminations> reportable) {
        reportableRepo.deleteAll();
        reportableRepo.saveAll(reportable);
    }

    @Override
    public void clearReportableTerminations() {
        reportableRepo.deleteAll();
    }

    // method to run stored procedure uspUpdateWorkingTables
    @Override
    public void createReportableTerminations() {                              
        //create data source for use with                
        DataSource ds = createDataSource();
               
        SimpleJdbcCall call = new SimpleJdbcCall(ds);
        call.withProcedureName("uspUpdateWorkingTables").execute();                       
    }

    //support function for building datasource
    public DataSource createDataSource(){
        DataSourceBuilder builder = DataSourceBuilder.create();
        builder.driverClassName(driver);
        builder.url(url);
        builder.username(username);
        builder.password(password);
        return builder.build();
    }

    // method to run stored procedure uspCreateUploadTable
    @Override
    public void createUploadTable() {
        //create datasource
        DataSource ds = createDataSource();

        //call stored procedure
        SimpleJdbcCall call = new SimpleJdbcCall(ds);
        call.withProcedureName("uspCreateUploadTable").execute();   
    }

    // method to run stored procedure uspUpdateReportedTerminations
    @Override
    public void updateReportedTerminations() {
        //create datasource
        DataSource ds = createDataSource();

        //call stored procedure
        SimpleJdbcCall call = new SimpleJdbcCall(ds);
        call.withProcedureName("uspUpdateReportedTerminations").execute();
    }

    @Override
    public void clearActiveStaff() {
        activeRepo.deleteAll();
    }

    @Override
    public void clearImportedData() {
        importedRepo.deleteAll();
    }

    @Override
    public void clearExportData() {
        exportRepo.deleteAll();
    }

    @Override
    public List<ReportedTerminations> getReportedTerminations() {
        return reportedRepo.findAll(Sort.by(Sort.Direction.ASC, "staffId"));
    }

    @Override
    public void deleteReportedById(String id) {
        reportedRepo.deleteById(id);
    }
    
}
