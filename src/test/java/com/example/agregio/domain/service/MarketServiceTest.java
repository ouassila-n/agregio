package com.example.agregio.domain.service;

import com.example.agregio.domain.model.Market;
import com.example.agregio.domain.repository.MarketRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MarketServiceTest {

    @InjectMocks
    private MarketServiceImpl marketService;

    @Mock
    private MarketRepository marketRepository;

    @Test
    void should_create_market_successfully() {
        // Given
        final var market = new Market("Market1");

        // When
        marketService.createMarket(market);

        // Then
        verify(marketRepository).save(market);

    }
}