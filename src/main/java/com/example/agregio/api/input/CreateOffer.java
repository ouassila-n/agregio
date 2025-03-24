package com.example.agregio.api.input;

import java.util.List;

import com.example.agregio.domain.model.Block;

public record CreateOffer(String company,
                          String marketName,
                          List<Block> blocks) {
}
