package com.example.agregio.domain.model;

import java.util.List;

public record Offer(
        String company,
        List<Block> blocks,
        Market market
) {
}
