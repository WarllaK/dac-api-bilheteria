package com.dac.bilheteria_api;

import com.dac.bilheteria_api.domain.enums.TicketStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import com.dac.bilheteria_api.repository.TicketRepository;
import com.dac.bilheteria_api.service.TicketService;
import com.dac.bilheteria_api.domain.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
@ExtendWith(MockitoExtension.class)
class BilheteriaApiApplicationTests {

	@Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketService ticketService;

    private Ticket validTicket;

    @BeforeEach
    void setUp() {
        validTicket = new Ticket();
        validTicket.setCode(UUID.randomUUID());
        validTicket.setStatus(TicketStatus.VALID);
    }

    @Test
    void shouldConfirmValidTicket() {
        when(ticketRepository.findByCode(validTicket.getCode())).thenReturn(Optional.of(validTicket));

        assertDoesNotThrow(() -> ticketService.confirmTicket(validTicket.getCode()));
        assertEquals(TicketStatus.USED, validTicket.getStatus());
        verify(ticketRepository).save(validTicket);
    }

    @Test
    void teste() {

    }
}
