package com.dac.bilheteria_api.repository;

import com.dac.bilheteria_api.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Optional<Ticket> findByCode(UUID code);
}