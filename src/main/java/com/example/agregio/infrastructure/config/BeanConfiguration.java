package com.example.agregio.infrastructure.config;

import com.example.agregio.domain.repository.MarketRepository;
import com.example.agregio.domain.repository.OfferRepository;
import com.example.agregio.domain.repository.ParkRepository;
import com.example.agregio.domain.service.MarketService;
import com.example.agregio.domain.service.MarketServiceImpl;
import com.example.agregio.domain.service.OfferService;
import com.example.agregio.domain.service.OfferServiceImpl;
import com.example.agregio.domain.service.ParkService;
import com.example.agregio.domain.service.ParkServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class BeanConfiguration {

    @Bean
    public MarketService marketService(final MarketRepository marketRepository) {
        return new MarketServiceImpl(marketRepository);
    }

    @Bean
    public ParkService parkService(final ParkRepository parkRepository) {
        return new ParkServiceImpl(parkRepository);
    }

    @Bean
    public OfferService offerService(final OfferRepository offerRepository) {
        return new OfferServiceImpl(offerRepository);
    }
}
