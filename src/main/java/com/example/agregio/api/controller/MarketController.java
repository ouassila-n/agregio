package com.example.agregio.api.controller;

import com.example.agregio.api.input.CreateMarket;
import com.example.agregio.api.mapper.MarketMapper;
import com.example.agregio.domain.service.MarketService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/markets")
@AllArgsConstructor
@Slf4j
public class MarketController {

    private final MarketService marketService;
    private final MarketMapper mapper;

    @PostMapping
    @Operation(summary = "Create new market")
    public void createMarket(@RequestBody CreateMarket request) {
        log.trace("Creating market: {}", request);
        marketService.createMarket(mapper.toDomain(request));
    }
}