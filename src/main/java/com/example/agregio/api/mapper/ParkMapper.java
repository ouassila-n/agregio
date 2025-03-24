package com.example.agregio.api.mapper;

import com.example.agregio.api.input.CreatePark;
import com.example.agregio.domain.model.Park;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ParkMapper {

    ParkMapper INSTANCE = Mappers.getMapper(ParkMapper.class);

    @Mapping(target = "type", source = "typePark")
    Park toDomain(CreatePark createPark);

}
