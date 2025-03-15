package com.dac.bilheteria_api.service;

import com.dac.bilheteria_api.domain.Ticket;
import com.dac.bilheteria_api.domain.enums.TicketStatus;
import com.dac.bilheteria_api.repository.TicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public boolean validateTicket(UUID code) {
        return ticketRepository.findByCode(code)
                .map(ticket -> ticket.getStatus() == TicketStatus.VALID)
                .orElse(false);
    }

    @Transactional
    public void confirmTicket(UUID code) {
        Ticket ticket = ticketRepository.findByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("Ingresso não encontrado"));


        if (ticket.getStatus() != TicketStatus.VALID) {
            throw new IllegalStateException("Ingresso já utilizado ou inválido");
        }

        ticket.invalidate();
        ticketRepository.save(ticket);
    }

}
