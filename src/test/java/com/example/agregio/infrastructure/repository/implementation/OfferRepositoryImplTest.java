package com.example.agregio.infrastructure.repository.implementation;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.agregio.domain.model.Block;
import com.example.agregio.domain.model.Market;
import com.example.agregio.domain.model.Offer;
import com.example.agregio.infrastructure.entity.BlockEntity;
import com.example.agregio.infrastructure.entity.MarketEntity;
import com.example.agregio.infrastructure.entity.OfferEntity;
import com.example.agregio.infrastructure.exception.MarketNotFoundException;
import com.example.agregio.infrastructure.mapper.OfferMapperInfra;
import com.example.agregio.infrastructure.repository.jpa.JpaMarketRepository;
import com.example.agregio.infrastructure.repository.jpa.JpaOfferRepository;
import com.example.agregio.infrastructure.repository.jpa.JpaParkRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OfferRepositoryImplTest {

    @InjectMocks
    private OfferRepositoryImpl offerRepositoryImpl;

    @Mock
    private OfferMapperInfra offerMapperInfra;

    @Mock
    private JpaOfferRepository jpaOfferRepository;

    @Mock
    private JpaParkRepository jpaParkRepository;

    @Mock
    private JpaMarketRepository jpaMarketRepository;

    public static final double MIN_PRICE = 4.5;
    public static final double ENERGY = 50.67;
    public static final int DURATION = 7;
    public static final String COMPANY_NAME = "Company";
    public static final String MARKET_NAME = "market";

    private Offer offer;
    private OfferEntity offerEntity;

    @BeforeEach
    void setUp() {
        //domain
        final var block = new Block(MIN_PRICE, ENERGY, DURATION);
        offer = new Offer(COMPANY_NAME, List.of(block), new Market(MARKET_NAME));

        //entities
        final var blockEntity = BlockEntity.builder().price(MIN_PRICE).energy(ENERGY).duration(DURATION).build();
        final var marketEntity = new MarketEntity(UUID.randomUUID(), MARKET_NAME, new ArrayList<>());
        offerEntity = new OfferEntity(UUID.randomUUID(), COMPANY_NAME, List.of(blockEntity), marketEntity);
    }

    @Test
    void should_save_offer_successfully() {
        // Given
        when(offerMapperInfra.toEntity(offer)).thenReturn(offerEntity);
        when(jpaMarketRepository.findByName(MARKET_NAME)).thenReturn(Optional.of(new MarketEntity()));
        when(jpaOfferRepository.save(offerEntity)).thenReturn(offerEntity);

        // When
        offerRepositoryImpl.save(offer);

        // Then
        verify(jpaOfferRepository).save(offerEntity);
        verify(jpaMarketRepository).findByName(MARKET_NAME);
    }

    @Test
    void should_throw_exception_when_market_not_found() {
        // Given
        when(offerMapperInfra.toEntity(offer)).thenReturn(offerEntity);
        when(jpaMarketRepository.findByName(MARKET_NAME)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(MarketNotFoundException.class, () -> offerRepositoryImpl.save(offer));
    }

    @Test
    void should_get_offers_by_company_successfully() {
        // Given
        when(jpaOfferRepository.findAllByCompany(COMPANY_NAME)).thenReturn(List.of(offerEntity));
        when(offerMapperInfra.toDomain(offerEntity)).thenReturn(offer);

        // When
        final var offers = offerRepositoryImpl.getOffersByCompany(COMPANY_NAME);

        // Then
        verify(jpaOfferRepository).findAllByCompany(COMPANY_NAME);
        verify(offerMapperInfra).toDomain(offerEntity);
        assertEquals(1, offers.size());
        assertTrue(offers.contains(offer));
    }
}