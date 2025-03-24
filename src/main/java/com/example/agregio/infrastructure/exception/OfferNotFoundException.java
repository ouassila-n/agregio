package com.example.agregio.infrastructure.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OfferNotFoundException extends RuntimeException {
    public OfferNotFoundException(String message) {
        super(message);
        log.error(message);
    }

    public OfferNotFoundException() {
        log.error("Offer not found");
    }
}
