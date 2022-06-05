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

import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class NowRateServiceTest {

    private static final String BAD_CURRENCY = "EMF";

    private static final String CURRENCY = "RUB";

    private static final Double RATE = 64.2345;


    @MockBean
    private CurrencyApiService currencyApiService;

    @Autowired
    private NowRateService nowRateService;

    @Test
    void getNowRateTest() {
        ResponseCurrencyApi responseCurrencyApi = new ResponseCurrencyApi();
        responseCurrencyApi.setRates(Map.of(CURRENCY, RATE));
        when(currencyApiService.getCurrency(anyString(), anyString())).thenReturn(responseCurrencyApi);
        Assertions.assertEquals(nowRateService.getNowRate(CURRENCY), RATE);
        verify(currencyApiService).getCurrency(anyString(), anyString());
    }

    @Test
    void getNowRateThrowsBadRequestExceptionTest() {
        ResponseCurrencyApi responseCurrencyApi = new ResponseCurrencyApi();
        responseCurrencyApi.setRates(Map.of(CURRENCY, RATE));
        when(currencyApiService.getCurrency(anyString(), anyString())).thenReturn(responseCurrencyApi);
        Assertions.assertThrows(BadRequestException.class, () -> nowRateService.getNowRate(BAD_CURRENCY));
        verify(currencyApiService).getCurrency(anyString(), anyString());
    }
}
