package com.example.agregio.domain.service;

import java.util.List;

import com.example.agregio.domain.repository.ParkRepository;
import com.example.agregio.domain.model.Park;

public class ParkServiceImpl implements ParkService {

    private final ParkRepository parkRepository;

    public ParkServiceImpl(ParkRepository parkRepository) {
        this.parkRepository = parkRepository;
    }

    @Override
    public void createPark(Park park) {
        parkRepository.save(park);
    }

    @Override
    public List<Park> getParksByMarket(String marketName) {
        return parkRepository.getParksPerMarket(marketName);
    }
}
