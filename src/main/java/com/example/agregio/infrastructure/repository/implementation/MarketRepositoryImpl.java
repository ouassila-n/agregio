package com.example.agregio.infrastructure.repository.implementation;

import com.example.agregio.domain.repository.MarketRepository;
import com.example.agregio.domain.model.Market;
import com.example.agregio.infrastructure.entity.MarketEntity;
import com.example.agregio.infrastructure.repository.jpa.JpaMarketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class MarketRepositoryImpl implements MarketRepository {

    private final JpaMarketRepository jpaMarketRepository;

    public MarketRepositoryImpl(JpaMarketRepository jpaMarketRepository) {
        this.jpaMarketRepository = jpaMarketRepository;
    }

    @Override
    public void save(Market market) {
        final var marketEntity = MarketEntity.builder().name(market.name()).build();
        final var marketSaved = jpaMarketRepository.save(marketEntity);
        log.trace("Market saved with id '{}'", marketSaved.getId());
    }
}