package ru.tbank.fdsspring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/currencies")
public class UserController {
    @GetMapping
    public ResponseEntity<?> getCurrencies() {
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> postCurrencies() {
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCurrenciesById(@PathVariable String id) {
        return ResponseEntity.ok().build();
    }
    @PostMapping("/{id}")
    public ResponseEntity<?> postCurrenciesById(@PathVariable String id) {
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCurrenciesById(@PathVariable String id) {
        return ResponseEntity.ok().build();
    }
}