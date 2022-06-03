package ru.alpha.test.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.alpha.test.exception.BadRequestException;
import ru.alpha.test.model.dto.ResponseGif;

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
    public String getGif(String currency) {
        log.info("Currency is {}", currency);
        String tag = currencyService.giveInfo(currency);
        log.info("Tag is {}", tag);

        ResponseEntity<ResponseGif> gif = gifApiService.getGif(tag);
        try {
            String embed_url = gif.getBody().getData().getEmbed_url();
            log.info("URL is {}", embed_url);
            return embed_url;
        } catch (NullPointerException e) {
            throw new BadRequestException("No such currency code found");
        }
    }
}
