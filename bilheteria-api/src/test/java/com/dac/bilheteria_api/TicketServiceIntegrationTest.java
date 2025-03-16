package com.dac.bilheteria_api;

import com.dac.bilheteria_api.domain.Ticket;
import com.dac.bilheteria_api.domain.enums.TicketStatus;
import com.dac.bilheteria_api.repository.TicketRepository;
import com.dac.bilheteria_api.service.TicketService;
import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.yml")
@RunWith(SpringRunner.class)
public class TicketServiceIntegrationTest {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketService ticketService;

    @Test
    @Transactional
    public void shouldNotValidateUsedTicket() {
        // Criar um ticket com status 'USED'
        Ticket usedTicket = new Ticket();
        usedTicket.setCode(UUID.randomUUID());
        usedTicket.setEventId(1L);
        usedTicket.setUserId(1L);
        usedTicket.setStatus(TicketStatus.USED);

        ticketRepository.save(usedTicket);

        boolean isValid = ticketService.validateTicket(usedTicket.getCode());

        System.out.println("isValid => " + isValid);

        assertFalse(isValid, "O ticket utilizado não deve ser válido.");
    }
}