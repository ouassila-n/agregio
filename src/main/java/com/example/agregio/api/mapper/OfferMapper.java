package com.example.agregio.api.mapper;

import com.example.agregio.api.input.CreateOffer;
import com.example.agregio.domain.model.Offer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OfferMapper {
    OfferMapper INSTANCE = Mappers.getMapper(OfferMapper.class);

    @Mapping(target = "market", expression = "java(new Market(createOffer.marketName()))")
    Offer toDomain(CreateOffer createOffer);
}
