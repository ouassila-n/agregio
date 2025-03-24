package com.example.agregio.api.input;

import com.example.agregio.domain.enums.TypePark;

public record CreatePark(
        TypePark typePark,
        Integer duration,
        Double energy
) {
}
