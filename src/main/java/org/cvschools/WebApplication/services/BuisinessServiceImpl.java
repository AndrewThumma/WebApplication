package org.cvschools.WebApplication.services;

import java.util.List;

import javax.sql.DataSource;

import org.cvschools.WebApplication.entities.ReportableTerminations;
import org.cvschools.WebApplication.repositories.ActiveStaffRepository;
import org.cvschools.WebApplication.repositories.ExportEmployeeRepository;
import org.cvschools.WebApplication.repositories.ImportedEmployeeRepository;
import org.cvschools.WebApplication.repositories.ReportableTerminationsRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BuisinessServiceImpl implements BuisinessService{

    //repositories
    private final ReportableTerminationsRepository repo;    
    private final ExportEmployeeRepository exportRepo;
    private final ImportedEmployeeRepository importedRep;
    private final ActiveStaffRepository activeRepo;

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
        return repo.findAll();
    }

    @Override
    public void updateReportableTerminations(List<ReportableTerminations> reportable) {
        repo.deleteAll();
        repo.saveAll(reportable);
    }

    @Override
    public void clearReportableTerminations() {
        repo.deleteAll();
    }

    // method to run stored procedure uspUpdateWorkingTables
    @Override
    public void createReportableTerminations() {        
        //create data source for use with                
        DataSource ds = createDataSource();
        
        SimpleJdbcCall call = new SimpleJdbcCall(ds).withProcedureName("[403b].[uspUpdateWorkingTables]");
        call.execute();        
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
        SimpleJdbcCall call = new SimpleJdbcCall(ds).withProcedureName("[403b].[uspCreateUploadTable]");
        call.execute();
    }

    // method to run stored procedure uspUpdateReportedTerminations
    @Override
    public void updateReportedTerminations() {
        //create datasource
        DataSource ds = createDataSource();

        //call stored procedure
        SimpleJdbcCall call = new SimpleJdbcCall(ds).withProcedureName("[403b].[uspUpdateReportedTerminations]");
        call.execute();
    }

    @Override
    public void clearActiveStaff() {
        activeRepo.deleteAll();
    }

    @Override
    public void clearImportedData() {
        importedRep.deleteAll();
    }

    @Override
    public void clearExportData() {
        exportRepo.deleteAll();
    }
    
}
