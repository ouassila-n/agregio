package com.example.agregio.infrastructure.repository.jpa;


import java.util.Optional;
import java.util.UUID;

import com.example.agregio.infrastructure.entity.MarketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaMarketRepository extends JpaRepository<MarketEntity, UUID> {

    Optional<MarketEntity> findByName(String name);

}
