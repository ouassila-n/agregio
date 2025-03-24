package com.example.agregio.domain.model;

import java.util.List;

import com.example.agregio.domain.enums.TypePark;

public record Park(
        TypePark type,
        Double energy,
        Integer duration,
        List<Block> blocks
) {
}
