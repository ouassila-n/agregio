package com.example.agregio.infrastructure.repository.jpa;

import java.util.UUID;

import com.example.agregio.infrastructure.entity.ParkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaParkRepository extends JpaRepository<ParkEntity, UUID> {
}
