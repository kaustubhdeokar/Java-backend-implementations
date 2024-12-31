package com.example.shopping_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/shop")
public class ShoppingController {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/")
    public ResponseEntity<String> shop() {
        return new ResponseEntity<>("Shopping in process.", HttpStatus.OK);
    }

    @GetMapping("/make-payment")
    public String invokePaymentService() {
        String url = "http://payment-service/pay/initiate";
        return restTemplate.getForObject(url, String.class);
    }

}
