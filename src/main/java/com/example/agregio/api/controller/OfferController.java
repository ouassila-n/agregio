package com.example.agregio.api.controller;

import java.util.List;

import com.example.agregio.api.input.CreateOffer;
import com.example.agregio.api.mapper.OfferMapper;
import com.example.agregio.domain.model.Offer;
import com.example.agregio.domain.service.OfferService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/offers")
@AllArgsConstructor
@Slf4j
public class OfferController {

    private final OfferService offerService;
    private final OfferMapper mapper;

    @Operation(summary = "Create new offer with blocks")
    @PostMapping
    public void createOffer(@RequestBody @Valid CreateOffer request) {
        log.info("Creating offer: {}", request);
        offerService.createOffer(mapper.toDomain(request));
    }

    @Operation(summary = "Get offers by company")
    @GetMapping("/{company}")
    public List<Offer> getOffersByCompany(@PathVariable String company) {
        log.trace("Getting offers by company: {}", company);
        return offerService.getOffersByCompany(company);
    }
}
