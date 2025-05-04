package ru.tbank.currency_signal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tbank.currency_signal.entity.CurrencySignal;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CurrencySignalRepository extends JpaRepository<CurrencySignal, UUID> {

    List<CurrencySignal> findAllByDeletedFalse();

    Optional<CurrencySignal> findByIdAndDeletedFalse(UUID id);

    Optional<CurrencySignal> findByBaseCurrencyAndDeletedFalse(String baseCurrency);
}
