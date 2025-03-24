package com.example.agregio.infrastructure.mapper;


import com.example.agregio.domain.model.Market;
import com.example.agregio.infrastructure.entity.MarketEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring", uses = { OfferMapperInfra.class })
public interface MarketMapperInfra {
    MarketMapperInfra INSTANCE = Mappers.getMapper(MarketMapperInfra.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "offers", ignore = true)
    MarketEntity toEntity(Market market);

    @Mapping(source = "name", target = "name")
    Market toDomain(MarketEntity marketEntity);
}
