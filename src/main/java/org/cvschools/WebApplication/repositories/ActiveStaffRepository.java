package org.cvschools.WebApplication.repositories;

import org.cvschools.WebApplication.entities.ActiveStaff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActiveStaffRepository extends JpaRepository<ActiveStaff, String>{
    
}
