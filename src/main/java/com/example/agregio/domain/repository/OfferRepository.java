package com.example.agregio.domain.repository;

import java.util.List;

import com.example.agregio.domain.model.Offer;

public interface OfferRepository {
    void save(Offer offer);

    List<Offer> getOffersByCompany(String company);
}