package com.dac.bilheteria_api.config;

import com.dac.bilheteria_api.repository.TicketRepository;
import com.dac.bilheteria_api.service.TicketService;
import org.springframework.context.annotation.Bean;

public class TestConfig {
    @Bean
    public TicketService ticketService(TicketRepository ticketRepository) {
        return new TicketService(ticketRepository);
    }
}