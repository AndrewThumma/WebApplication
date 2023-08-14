package org.cvschools.WebApplication.repositories;

import org.cvschools.WebApplication.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * repository class for managing roles in database
 */

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}

