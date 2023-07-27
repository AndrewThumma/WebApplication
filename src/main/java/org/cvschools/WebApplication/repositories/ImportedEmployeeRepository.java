package org.cvschools.WebApplication.repositories;

import org.cvschools.WebApplication.entities.ImportedEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImportedEmployeeRepository extends JpaRepository<ImportedEmployee, String>{
    
}
