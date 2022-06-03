package ru.alpha.test.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.alpha.test.exception.BadRequestException;
import ru.alpha.test.model.dto.ResponseCurrencyApi;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class CurrencyServiceImpl implements CurrencyService {


    private final CurrencyApiService currencyApiService;

    public CurrencyServiceImpl(CurrencyApiService currencyApiService) {
        this.currencyApiService = currencyApiService;
    }

    @Override
    public String giveInfo(String currency) {

        String nowDateStr = LocalDate.now(ZoneId.of("US/Eastern")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        log.info("Date now {}" ,nowDateStr);
        String yestDateStr = LocalDate.now(ZoneId.of("US/Eastern")).minusDays(1L).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        log.info("Date yesterday {}", yestDateStr);

        ResponseCurrencyApi currencyNow = currencyApiService.getCurrency(nowDateStr);
        ResponseCurrencyApi currencyYest = currencyApiService.getCurrency(yestDateStr);

        if(!currencyNow.getRates().containsKey(currency)){
            throw new BadRequestException("This currency is not listed or does not exist");
        }


        Double now = currencyNow.getRates().get(currency);
        log.info("Currency for {} on {} is {} ",currency,nowDateStr,now);
        Double yest = currencyYest.getRates().get(currency);
        log.info("Currency for {} on {} is {} ",currency,yestDateStr,yest);

        if (now > yest) {
            return "rich";
        }
        if (now < yest) {
            return "broke";
        } else {
            return "equals";
        }

    }
}
