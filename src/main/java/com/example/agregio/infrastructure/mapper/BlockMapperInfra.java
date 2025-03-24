package com.example.agregio.infrastructure.mapper;


import com.example.agregio.domain.model.Block;
import com.example.agregio.infrastructure.entity.BlockEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = { OfferMapperInfra.class })
public interface BlockMapperInfra {
    BlockMapperInfra INSTANCE = Mappers.getMapper(BlockMapperInfra.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "offer", ignore = true)
    @Mapping(target = "price", source = "minPrice")
    BlockEntity toEntity(Block block);

    @Mapping(source = "price", target = "minPrice")
    Block toDomain(BlockEntity blockEntity);
}
