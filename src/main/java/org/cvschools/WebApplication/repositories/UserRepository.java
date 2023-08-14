package org.cvschools.WebApplication.repositories;

import org.cvschools.WebApplication.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * repository class used to manage users in database
 */

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
