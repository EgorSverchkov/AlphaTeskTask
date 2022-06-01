package ru.alpha.test.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GifServiceImpl implements GifService {
    private final CurrencyService currencyService;

    private final GifApiService gifApiService;

    public GifServiceImpl(CurrencyService currencyService, GifApiService gifApiService) {
        this.currencyService = currencyService;
        this.gifApiService = gifApiService;
    }

    @Override
    public Map getGif() {
        String tag = currencyService.giveInfo();

        ResponseEntity<Map> gif = gifApiService.getGif(tag);

        return gif.getBody();
    }
}
