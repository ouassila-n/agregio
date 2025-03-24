package com.example.agregio.infrastructure.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MarketNotFoundException extends RuntimeException {
    public MarketNotFoundException(String message) {
        super(message);
        log.error(message);
    }

    public MarketNotFoundException() {
        log.error("Market not found");
    }
}
