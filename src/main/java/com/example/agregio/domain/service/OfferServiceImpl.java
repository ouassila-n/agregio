package com.example.agregio.domain.service;

import java.util.List;

import com.example.agregio.domain.repository.OfferRepository;
import com.example.agregio.domain.model.Offer;

public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public void createOffer(Offer offer) {
        offerRepository.save(offer);
    }

    @Override
    public List<Offer> getOffersByCompany(String company) {
        return offerRepository.getOffersByCompany(company);
    }
}
