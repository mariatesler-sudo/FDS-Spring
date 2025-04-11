package ru.tbank.currency_signal.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tbank.currency_signal.entity.Currency;
import ru.tbank.currency_signal.repository.CurrencyRepository;

import java.util.List;

@Service
public class CurrencyService {
    private final CurrencyRepository currencyRepository;

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public List<Currency> findAll() {
        return currencyRepository.findAll();
    }

    public Currency findById(Long id) {
        return currencyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Currency not found with id: " + id));
    }

    @Transactional
    public Currency save(Currency currency) {
        if (currencyRepository.existsByCode(currency.getCode())) {
            throw new RuntimeException("Currency code already exists: " + currency.getCode());
        }
        return currencyRepository.save(currency);
    }

    @Transactional
    public Currency update(Long id, Currency currency) {
        Currency existing = findById(id);
        existing.setName(currency.getName());
        existing.setRate(currency.getRate());
        existing.setActive(currency.getActive());
        return currencyRepository.save(existing);
    }

    @Transactional
    public void deleteById(Long id) {
        currencyRepository.deleteById(id);
    }
}