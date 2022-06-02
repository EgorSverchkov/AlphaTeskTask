package ru.alpha.test.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.alpha.test.model.dto.Gif;
import ru.alpha.test.model.dto.ResponseGif;

import java.util.Map;

@Component
@Slf4j
public class GifServiceImpl implements GifService {
    private final CurrencyService currencyService;

    private final GifApiService gifApiService;

    public GifServiceImpl(CurrencyService currencyService, GifApiService gifApiService) {
        this.currencyService = currencyService;
        this.gifApiService = gifApiService;
    }

    @Override
    public String getGif() {
        String tag = currencyService.giveInfo();

        ResponseEntity<ResponseGif> gif = gifApiService.getGif(tag);

        return gif.getBody().getData().getEmbed_url();
    }
}
