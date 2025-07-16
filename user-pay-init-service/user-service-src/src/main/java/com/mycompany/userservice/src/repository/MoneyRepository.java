package com.mycompany.userservice.src.repository;

import com.mycompany.userservice.src.model.entity.Money;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface MoneyRepository extends JpaRepository<Money, UUID> {
}
