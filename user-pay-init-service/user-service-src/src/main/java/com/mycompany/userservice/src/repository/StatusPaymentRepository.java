package com.mycompany.userservice.src.repository;

import com.mycompany.userservice.src.model.StatusPaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface StatusPaymentRepository extends JpaRepository<StatusPaymentEntity, UUID> {
    Optional<StatusPaymentEntity> findByPaymentId(UUID paymentId);
}
