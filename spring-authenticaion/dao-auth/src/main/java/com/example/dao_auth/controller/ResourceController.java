package com.example.dao_auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/private")
public class ResourceController {

    @GetMapping("/resource")
    public ResponseEntity<String> getResource(){
        return ResponseEntity.ok("resource");
    }


}
