package com.mycompany.userservice.src.repository;

import com.mycompany.userservice.rest.model.PaymentTransactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


/**
 * JpaRepository для PaymentTransactional.
 */
@Repository
public interface PaymentTransactionalRepository extends JpaRepository<PaymentTransactional, UUID> {
}
