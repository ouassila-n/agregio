package com.example.agregio.infrastructure.repository.implementation;

import com.example.agregio.domain.model.Block;
import com.example.agregio.domain.repository.BlockRepository;
import com.example.agregio.infrastructure.entity.BlockEntity;
import com.example.agregio.infrastructure.repository.jpa.JpaBlockRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class BlockRepositoryImpl implements BlockRepository {
    private final JpaBlockRepository jpaBlockRepository;

    @Override
    public void save(Block block) {
        final var blockEntity = BlockEntity.builder()
                .duration(block.duration())
                .energy(block.energy())
                .price(block.minPrice())
                .build();
        final var blockSaved = jpaBlockRepository.save(blockEntity);

        log.trace("Block saved with id '{}'", blockSaved.getId());
    }
}
