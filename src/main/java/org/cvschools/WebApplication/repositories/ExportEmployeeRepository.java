package org.cvschools.WebApplication.repositories;

import org.cvschools.WebApplication.entities.ExportEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExportEmployeeRepository extends JpaRepository<ExportEmployee, String>{
    
}
