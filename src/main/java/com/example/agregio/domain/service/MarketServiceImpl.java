package com.example.agregio.domain.service;

import com.example.agregio.domain.repository.MarketRepository;
import com.example.agregio.domain.model.Market;


public class MarketServiceImpl implements MarketService {

    private final MarketRepository marketRepository;

    public MarketServiceImpl(MarketRepository marketRepository) {
        this.marketRepository = marketRepository;
    }

    @Override
    public void createMarket(Market market) {
        marketRepository.save(market);
    }
}
