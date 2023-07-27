package org.cvschools.WebApplication.repositories;

import org.cvschools.WebApplication.entities.ReportableTerminations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportableTerminationsRepository extends JpaRepository<ReportableTerminations, String>{
    
}
