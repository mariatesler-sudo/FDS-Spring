package ru.tbank.currency_signal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tbank.currency_signal.entity.CurrencySignal;
import ru.tbank.currency_signal.service.CurrencySignalService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/currency-signals")
@RequiredArgsConstructor
public class CurrencySignalController {

    private final CurrencySignalService currencySignalService;

    @GetMapping
    public ResponseEntity<List<CurrencySignal>> findAll() {
        return ResponseEntity.ok(currencySignalService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CurrencySignal> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(currencySignalService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CurrencySignal> create(@RequestBody CurrencySignal currencySignal) {
        return ResponseEntity.ok(currencySignalService.save(currencySignal));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CurrencySignal> update(@PathVariable UUID id, @RequestBody CurrencySignal currencySignal) {
        return ResponseEntity.ok(currencySignalService.update(id, currencySignal));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        currencySignalService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
