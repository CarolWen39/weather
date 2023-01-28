package com.example.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class SearchController {
    @Value("${server.port}")
    private int randomServerPort;
    @GetMapping("/weather/search")
    public ResponseEntity<?> getDetails() {
        return new ResponseEntity<>("this is search service", HttpStatus.OK);
    }

    @GetMapping("/search/port")
    public ResponseEntity<?> queryWeatherByCity() {
        return new ResponseEntity<>("search service + " + randomServerPort, HttpStatus.OK);
    }
}
