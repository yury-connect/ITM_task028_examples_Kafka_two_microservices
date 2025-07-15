package com.mycompany.userservice.src.repository;

import com.mycompany.userservice.rest.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}
