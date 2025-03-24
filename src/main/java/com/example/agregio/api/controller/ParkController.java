package com.example.agregio.api.controller;

import java.util.List;

import com.example.agregio.api.input.CreatePark;
import com.example.agregio.api.mapper.ParkMapper;
import com.example.agregio.domain.model.Park;
import com.example.agregio.domain.service.ParkService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parks")
@AllArgsConstructor
@Slf4j
public class ParkController {

    private final ParkService parkService;
    private final ParkMapper mapper;

    @Operation(summary = "Create new park")
    @PostMapping
    public void createPark(@RequestBody CreatePark request) {
        log.trace("Creating park: {}", request);
        parkService.createPark(mapper.toDomain(request));
    }


    @Operation(summary = "Get parks per market")
    @GetMapping("/{marketName}")
    public List<Park> getParksPerMarket(@PathVariable String marketName) {
        log.trace("Getting parks by market: {}", marketName);
        return parkService.getParksByMarket(marketName);
    }
}
