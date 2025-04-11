package ru.tbank.currency_signal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tbank.currency_signal.entity.Currency;
import ru.tbank.currency_signal.service.CurrencyService;

import java.util.List;

@RestController
@RequestMapping("/api/currencies")
public class CurrencyController {
    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping
    public ResponseEntity<List<Currency>> getAll() {
        return ResponseEntity.ok(currencyService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Currency> getById(@PathVariable Long id) {
        return ResponseEntity.ok(currencyService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Currency> create(@RequestBody Currency currency) {
        return ResponseEntity.status(HttpStatus.CREATED).body(currencyService.save(currency));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Currency> update(
            @PathVariable Long id,
            @RequestBody Currency currency) {
        return ResponseEntity.ok(currencyService.update(id, currency));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        currencyService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}