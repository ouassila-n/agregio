package com.example.agregio.domain.service;

import java.util.List;

import com.example.agregio.domain.model.Block;
import com.example.agregio.domain.model.Market;
import com.example.agregio.domain.model.Offer;
import com.example.agregio.domain.repository.OfferRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class OfferServiceImplTest {

    @InjectMocks
    private OfferServiceImpl offerService;

    @Mock
    private OfferRepository offerRepository;

    @Test
    void should_create_offer_successfully() {
        // Given
        final var offer = new Offer("Company", List.of(new Block(4.5, 50.67, 7)), new Market("market"));

        // When
        offerService.createOffer(offer);

        // Then
        verify(offerRepository).save(offer);
    }

    @Test
    void should_get_offers_by_company_successfully() {
        // Given
        final var company = "Company";

        // When
        offerService.getOffersByCompany(company);

        // Then
        verify(offerRepository).getOffersByCompany(company);
    }
}