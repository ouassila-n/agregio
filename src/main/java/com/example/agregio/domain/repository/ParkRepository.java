package com.example.agregio.domain.repository;

import java.util.List;

import com.example.agregio.domain.model.Park;

public interface ParkRepository {
    void save(Park park);

    List<Park> getParksPerMarket(String marketName);
}
