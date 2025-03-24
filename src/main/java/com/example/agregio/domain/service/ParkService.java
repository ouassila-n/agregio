package com.example.agregio.domain.service;

import java.util.List;
import com.example.agregio.domain.model.Park;

public interface ParkService {
    void createPark(Park park);

    List<Park> getParksByMarket(String marketName);

}
