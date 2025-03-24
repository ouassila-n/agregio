package com.example.agregio.domain.service;

import java.util.List;

import com.example.agregio.domain.model.Offer;

public interface OfferService {
    void createOffer(Offer offer);

    List<Offer> getOffersByCompany(String company) ;

}
