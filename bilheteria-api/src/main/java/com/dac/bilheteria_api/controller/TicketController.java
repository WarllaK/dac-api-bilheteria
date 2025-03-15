package com.dac.bilheteria_api.controller;

import com.dac.bilheteria_api.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    TicketService ticketService;


    @GetMapping("/validate/{code}")
    public ResponseEntity<Boolean> validateTicket(@PathVariable UUID code) {
        boolean isValid = ticketService.validateTicket(code);
        return ResponseEntity.ok(isValid);
    }

    @PostMapping("/confirm/{code}")
    public ResponseEntity<String> confirmTicket(@PathVariable UUID code) {
        ticketService.confirmTicket(code);
        return ResponseEntity.ok("Ingresso confirmado e invalidado.");
    }

}
