package com.example.payment_service.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay")
public class PaymentController {

    @GetMapping("/initiate")
    public ResponseEntity<String> doPayment(HttpServletRequest request) {
        String clientHost = request.getRemoteHost();
        int clientPort = request.getRemotePort();
        String responseMessage = String.format("Payment in process. Request received from host: %s, port: %d", clientHost, clientPort);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @GetMapping("/status")
    public ResponseEntity<String> getPaymentStatus() {
        return new ResponseEntity<>("Payment done.", HttpStatus.OK);
    }

}

