package com.example.agregio.infrastructure.repository.implementation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.agregio.domain.enums.TypePark;
import com.example.agregio.domain.model.Park;
import com.example.agregio.infrastructure.entity.BlockEntity;
import com.example.agregio.infrastructure.entity.MarketEntity;
import com.example.agregio.infrastructure.entity.OfferEntity;
import com.example.agregio.infrastructure.entity.ParkEntity;
import com.example.agregio.infrastructure.exception.MarketNotFoundException;
import com.example.agregio.infrastructure.mapper.BlockMapperInfra;
import com.example.agregio.infrastructure.repository.jpa.JpaMarketRepository;
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
class ParkRepositoryImplTest {

    @InjectMocks
    private ParkRepositoryImpl parkRepositoryImpl;

    @Mock
    private JpaParkRepository jpaParkRepository;

    @Mock
    private JpaMarketRepository jpaMarketRepository;

    @Mock
    private BlockMapperInfra blockMapperInfra;

    public static final double ENERGY = 100.0;
    public static final int DURATION = 24;
    public static final String MARKET_NAME = "market";
    public static final String COMPANY_NAME = "Company";

    private Park park;
    private ParkEntity parkEntity;
    private MarketEntity marketEntity;

    @BeforeEach
    void setUp() {
        park = new Park(TypePark.SOLAR, ENERGY, DURATION, List.of());
        parkEntity = ParkEntity.builder()
                .id(UUID.randomUUID())
                .type(TypePark.SOLAR.name())
                .energy(ENERGY)
                .duration(DURATION)
                .build();
        marketEntity = new MarketEntity(UUID.randomUUID(), MARKET_NAME, List.of());
    }

    @Test
    void should_save_park_successfully() {
        // Given
        when(jpaParkRepository.save(any(ParkEntity.class))).thenReturn(parkEntity);

        // When
        parkRepositoryImpl.save(park);

        // Then
        verify(jpaParkRepository).save(any(ParkEntity.class));
    }

    @Test
    void should_get_parks_per_market_successfully() {
        // Given
        final var offerEntity = new OfferEntity(UUID.randomUUID(), COMPANY_NAME, List.of(), marketEntity);
        final var blockEntity = BlockEntity.builder().offer(offerEntity).build();
        parkEntity.setBlocks(List.of(blockEntity));

        when(jpaMarketRepository.findByName(MARKET_NAME)).thenReturn(Optional.of(marketEntity));
        when(jpaParkRepository.findAll()).thenReturn(List.of(parkEntity));
        when(blockMapperInfra.toDomain(blockEntity)).thenReturn(null);

        // When
        final var parks = parkRepositoryImpl.getParksPerMarket(MARKET_NAME);

        // Then
        verify(jpaMarketRepository).findByName(MARKET_NAME);
        verify(jpaParkRepository).findAll();
        assertEquals(1, parks.size());
        assertEquals(TypePark.SOLAR, parks.getFirst().type());
    }

    @Test
    void should_throw_exception_when_market_not_found() {
        // Given
        when(jpaMarketRepository.findByName(MARKET_NAME)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(MarketNotFoundException.class, () -> parkRepositoryImpl.getParksPerMarket(MARKET_NAME));
    }
}