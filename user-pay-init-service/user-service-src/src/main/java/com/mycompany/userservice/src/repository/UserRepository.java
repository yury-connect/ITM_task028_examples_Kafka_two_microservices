package com.mycompany.userservice.src.repository;

import com.mycompany.userservice.rest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * JpaRepository для User.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
