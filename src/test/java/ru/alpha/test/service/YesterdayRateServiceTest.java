package ru.alpha.test.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.alpha.test.exception.BadRequestException;
import ru.alpha.test.model.dto.ResponseCurrencyApi;
import ru.alpha.test.service.api.CurrencyApiService;

import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class YesterdayRateServiceTest {

    private static final String BAD_CURRENCY = "CCC";

    private static final String CURRENCY = "RUB";

    private static final Double RATE = 64.2345;

    @MockBean
    private CurrencyApiService currencyApiService;

    @Autowired
    private YesterdayRateService yesterdayRateService;

    @Test
    void getYesterdayRateTest() {
        ResponseCurrencyApi responseCurrencyApi = new ResponseCurrencyApi();
        responseCurrencyApi.setRates(Map.of(CURRENCY, RATE));
        when(currencyApiService.getCurrency(anyString(),anyString())).thenReturn(responseCurrencyApi);
        Assertions.assertEquals(yesterdayRateService.getYesterdayRate(CURRENCY), RATE);
        verify(currencyApiService).getCurrency(anyString(),anyString());
    }

    @Test
    void getNowRateThrowsBadRequestExceptionTest(){
        ResponseCurrencyApi responseCurrencyApi = new ResponseCurrencyApi();
        responseCurrencyApi.setRates(Map.of(CURRENCY, RATE));
        when(currencyApiService.getCurrency(anyString(),anyString())).thenReturn(responseCurrencyApi);
        Assertions.assertThrows(BadRequestException.class,() -> yesterdayRateService.getYesterdayRate(BAD_CURRENCY));
        verify(currencyApiService).getCurrency(anyString(),anyString());
    }
}
