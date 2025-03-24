package com.example.agregio.infrastructure.repository.implementation;

import java.util.List;

import com.example.agregio.domain.enums.TypePark;
import com.example.agregio.domain.model.Park;
import com.example.agregio.domain.repository.ParkRepository;
import com.example.agregio.infrastructure.entity.BlockEntity;
import com.example.agregio.infrastructure.entity.OfferEntity;
import com.example.agregio.infrastructure.entity.ParkEntity;
import com.example.agregio.infrastructure.exception.MarketNotFoundException;
import com.example.agregio.infrastructure.mapper.BlockMapperInfra;
import com.example.agregio.infrastructure.repository.jpa.JpaMarketRepository;
import com.example.agregio.infrastructure.repository.jpa.JpaParkRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class ParkRepositoryImpl implements ParkRepository {
    private final JpaParkRepository jpaParkRepository;
    private final JpaMarketRepository jpaMarketRepository;
    private BlockMapperInfra blockMapperInfra;

    @Override
    public void save(Park park) {
        final var parkEntity = ParkEntity
                .builder()
                .type(park.type().name())
                .energy(park.energy())
                .duration(park.duration())
                .build();

        final var parkSaved = jpaParkRepository.save(parkEntity);
        log.trace("Park saved with id '{}'", parkSaved.getId());
    }

    @Override
    public List<Park> getParksPerMarket(String marketName) {
        final var marketEntity = jpaMarketRepository.findByName(marketName)
                .orElseThrow(MarketNotFoundException::new);
        log.trace("Getting parks by market id '{}'", marketEntity.getId());

        return jpaParkRepository.findAll().stream()
                .filter(parkEntity -> parkEntity.getBlocks().stream()
                        .map(BlockEntity::getOffer)
                        .map(OfferEntity::getMarket)
                        .anyMatch(market -> market.getId().equals(marketEntity.getId())))
                .map(parkEntity -> new Park(
                        TypePark.valueOf(parkEntity.getType()),
                        parkEntity.getEnergy(),
                        parkEntity.getDuration(),
                        parkEntity.getBlocks().stream()
                                .filter(blockEntity -> blockEntity.getOffer().getMarket().getId().equals(marketEntity.getId()))
                                .map(blockMapperInfra::toDomain)
                                .toList()
                ))
                .toList();
    }
}
