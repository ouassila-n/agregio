package com.example.agregio.api.mapper;

import com.example.agregio.api.input.CreateMarket;
import com.example.agregio.domain.model.Market;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MarketMapper {
    MarketMapper INSTANCE = Mappers.getMapper(MarketMapper.class);

    Market toDomain(CreateMarket createMarket);
}
