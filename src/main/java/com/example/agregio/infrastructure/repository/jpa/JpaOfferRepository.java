package com.example.agregio.infrastructure.repository.jpa;

import java.util.List;
import java.util.UUID;

import com.example.agregio.infrastructure.entity.BlockEntity;
import com.example.agregio.infrastructure.entity.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaOfferRepository extends JpaRepository<OfferEntity, UUID> {
    List<OfferEntity> findAllByCompany(String company);
}
