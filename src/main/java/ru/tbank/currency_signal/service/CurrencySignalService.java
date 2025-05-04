package ru.tbank.currency_signal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tbank.currency_signal.entity.CurrencySignal;
import ru.tbank.currency_signal.repository.CurrencySignalRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CurrencySignalService {

    private final CurrencySignalRepository currencySignalRepository;

    public List<CurrencySignal> findAll() {
        return currencySignalRepository.findAllByDeletedFalse();
    }

    public CurrencySignal findById(UUID id) {
        return currencySignalRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("CurrencySignal not found with id: " + id));
    }

    public Optional<CurrencySignal> findByBaseCurrency(String baseCurrency) {
        return currencySignalRepository.findByBaseCurrencyAndDeletedFalse(baseCurrency);
    }

    @Transactional
    public CurrencySignal save(CurrencySignal currencySignal) {
        Optional<CurrencySignal> existingCurrency = currencySignalRepository.findByBaseCurrencyAndDeletedFalse(currencySignal.getBaseCurrency());
        if (existingCurrency.isPresent()) {
            throw new RuntimeException("Currency with base currency code " + currencySignal.getBaseCurrency() + " already exists");
        }
        return currencySignalRepository.save(currencySignal);
    }

    @Transactional
    public CurrencySignal update(UUID id, CurrencySignal currencySignal) {
        CurrencySignal existing = findById(id);
        existing.setName(currencySignal.getName());
        existing.setBaseCurrency(currencySignal.getBaseCurrency());
        existing.setPriceChangeRange(currencySignal.getPriceChangeRange());
        existing.setDescription(currencySignal.getDescription());
        return currencySignalRepository.save(existing);
    }

    @Transactional
    public void delete(UUID id) {
        CurrencySignal existing = findById(id);
        existing.setDeleted(true);
        currencySignalRepository.save(existing);
    }
}
