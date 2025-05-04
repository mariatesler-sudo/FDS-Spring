package ru.tbank.currency_signal.job;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.tbank.currency_signal.entity.CurrencySignal;
import ru.tbank.currency_signal.service.CurrencySignalService;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CurrencyJob {

    private final CurrencySignalService currencySignalService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Scheduled(cron = "0 0 * * * *")
    public void checkCurrencyRates() {
        try {
            JsonNode response = objectMapper.readTree(new URL("https://www.cbr-xml-daily.ru/daily_json.js"));

            JsonNode valuteNode = response.get("Valute");
            if (valuteNode != null) {
                Iterator<Map.Entry<String, JsonNode>> fields = valuteNode.fields();
                while (fields.hasNext()) {
                    Map.Entry<String, JsonNode> entry = fields.next();
                    String currencyCode = entry.getKey();
                    JsonNode currencyInfo = entry.getValue();

                    double value = currencyInfo.get("Value").asDouble();
                    double previous = currencyInfo.get("Previous").asDouble();

                    Optional<CurrencySignal> optionalCurrencySignal = currencySignalService.findByBaseCurrency(currencyCode);

                    if (optionalCurrencySignal.isPresent()) {
                        CurrencySignal currencySignal = optionalCurrencySignal.get();
                        String priceChangeRangeStr = currencySignal.getPriceChangeRange().replace("%", "").replace(",", ".");

                        double priceChangeRange = Double.parseDouble(priceChangeRangeStr); // Например: -1.0

                        double changePercent = ((value - previous) / previous) * 100;

                        if ((priceChangeRange < 0 && changePercent <= priceChangeRange) ||
                                (priceChangeRange > 0 && changePercent >= priceChangeRange)) {
                            System.out.println(currencySignal.getDescription());
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при получении курсов валют: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Ошибка обработки данных: " + e.getMessage());
        }
    }
}
