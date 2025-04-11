package ru.tbank.currency_signal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tbank.currency_signal.entity.Currency;

import java.util.List;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    boolean existsByCode(String code);

    List<Currency> findAll();
}

//vhbjnkml