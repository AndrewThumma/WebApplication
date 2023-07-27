package org.cvschools.WebApplication.repositories;

import org.cvschools.WebApplication.entities.ReportedTerminations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportedTerminationsRepository extends JpaRepository<ReportedTerminations, String>{
    
}
