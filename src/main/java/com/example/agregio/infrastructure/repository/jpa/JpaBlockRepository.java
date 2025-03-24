package com.example.agregio.infrastructure.repository.jpa;

import java.util.UUID;

import com.example.agregio.infrastructure.entity.BlockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaBlockRepository extends JpaRepository<BlockEntity, UUID> {
    boolean existsByDurationAndEnergyAndPrice(Integer duration, Double energy, Double price);
}
