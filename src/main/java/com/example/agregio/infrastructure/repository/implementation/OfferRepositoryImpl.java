package com.example.agregio.infrastructure.repository.implementation;

import java.util.List;
import java.util.Objects;

import com.example.agregio.domain.model.Offer;
import com.example.agregio.domain.repository.OfferRepository;
import com.example.agregio.infrastructure.entity.BlockEntity;
import com.example.agregio.infrastructure.exception.MarketNotFoundException;
import com.example.agregio.infrastructure.mapper.OfferMapperInfra;
import com.example.agregio.infrastructure.repository.jpa.JpaMarketRepository;
import com.example.agregio.infrastructure.repository.jpa.JpaOfferRepository;
import com.example.agregio.infrastructure.repository.jpa.JpaParkRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class OfferRepositoryImpl implements OfferRepository {
    private final OfferMapperInfra offerMapperInfra;
    private final JpaOfferRepository jpaOfferRepository;
    private final JpaMarketRepository jpaMarketRepository;
    private final JpaParkRepository jpaParkRepository;

    @Override
    public void save(Offer offer) {
        final var offerEntity = offerMapperInfra.toEntity(offer);
        offerEntity.getBlocks().forEach(block -> block.setOffer(offerEntity));
        //check if market exists before saving
        jpaMarketRepository.findByName(offer.market().name()).ifPresentOrElse(
                offerEntity::setMarket,
                () -> {
                    throw new MarketNotFoundException();
                });

        final var offerSaved = jpaOfferRepository.save(offerEntity);
        log.trace("Offer saved with id '{}'", offerSaved.getId());
        offerEntity.getBlocks().forEach(this::linkBlockWithPark);
    }

    @Override
    public List<Offer> getOffersByCompany(String company) {
        return jpaOfferRepository.findAllByCompany(company).stream()
                .map(offerMapperInfra::toDomain)
                .toList();
    }

    private void linkBlockWithPark(BlockEntity blockEntity) {
        log.trace("Linking blocks with park");
        jpaParkRepository.findAll().stream()
                .filter(parkEntity -> Objects.equals(parkEntity.getDuration(), blockEntity.getDuration()))
                .findAny()
                .ifPresent(parkEntity -> {
                    parkEntity.getBlocks().add(blockEntity);
                    jpaParkRepository.save(parkEntity);
                });
    }
}
