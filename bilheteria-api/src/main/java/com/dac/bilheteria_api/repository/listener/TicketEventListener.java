package com.dac.bilheteria_api.repository.listener;

import com.dac.bilheteria_api.domain.Ticket;
import com.dac.bilheteria_api.domain.TicketPublisherEvent;
import com.dac.bilheteria_api.repository.TicketRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TicketEventListener {

    @Autowired
    TicketRepository ticketRepository;


    @RabbitListener(queues = "ticketGenerationQueue")
    public void handleTicketPurchased(TicketPublisherEvent event) {
        for (TicketPublisherEvent.TicketItem item : event.getTickets()) {
            for (int i = 0; i < item.getQuantity(); i++) {
                Ticket ticket = new Ticket();
                ticket.setEventId(event.getEventId());
                ticket.setUserId(event.getUserId());
                ticket.setCode(UUID.randomUUID());

                ticketRepository.save(ticket);
            }
        }
    }
}
