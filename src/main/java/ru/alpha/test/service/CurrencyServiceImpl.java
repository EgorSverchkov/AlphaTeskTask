package ru.alpha.test.service;

import org.springframework.stereotype.Component;
import ru.alpha.test.model.dto.ResponseCurrencyApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyApiService currencyApiService;

    public CurrencyServiceImpl(CurrencyApiService currencyApiService) {
        this.currencyApiService = currencyApiService;
    }

    @Override
    public String giveInfo() {

        String nowDateStr = LocalDate.now().minusDays(1L).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String yestDateStr = LocalDate.now().minusDays(2L).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        ResponseCurrencyApi currency = currencyApiService.getCurrency(nowDateStr);
        ResponseCurrencyApi currency1 = currencyApiService.getCurrency(yestDateStr);

        Double nowRub = currency.getRates().get("RUB");
        Double yestRub = currency1.getRates().get("RUB");

        if (nowRub > yestRub) {
            return "rich";
        }
        if (nowRub < yestRub) {
            return "broke";
        } else {
            return "equals";
        }

    }
}
