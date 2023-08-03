package org.cvschools.WebApplication.repositories;

import java.util.List;

import org.cvschools.WebApplication.entities.ImportedEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ImportedEmployeeRepository extends JpaRepository<ImportedEmployee, String>{
    
    @Query(value = "SELECT * FROM [403b].ImportedData AS T1 WHERE terminationDate <> '' AND NOT EXISTS (SELECT * FROM [403b].ReportedTerminations AS S1 WHERE S1.StaffID = T1.StaffID)"
            , nativeQuery = true)
    List<ImportedEmployee> findReportableTerminations();

    @Query(value = "SELECT * FROM [403b].ImportedData WHERE terminationDate = ''", nativeQuery = true)
    List<ImportedEmployee> findActiveStaff();

}
