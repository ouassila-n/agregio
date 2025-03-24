package com.example.agregio.infrastructure.mapper;

import com.example.agregio.domain.model.Offer;
import com.example.agregio.infrastructure.entity.OfferEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = { BlockMapperInfra.class, MarketMapperInfra.class })
public interface OfferMapperInfra {

    OfferMapperInfra INSTANCE = Mappers.getMapper(OfferMapperInfra.class);

    Offer toDomain(OfferEntity entity);

    @Mapping(target = "id", ignore = true)
    OfferEntity toEntity(Offer offer);
}
