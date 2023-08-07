
package com.example.restandmicroservices.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/random")
public class RandomController {

    @GetMapping("/{str}")
    public ResponseEntity<RandomResponse> someRandom(@PathVariable String str) {
        System.out.println(str);
        return new ResponseEntity<>(new RandomResponse(), HttpStatus.OK);
    }

    private class RandomResponse {
        String link = "http://localhost:8080/users";
        String body = "msg";

        public String getLink() {
            return link;
        }

        public String getBody() {
            return body;
        }
    }

}
