package ru.alpha.test.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.alpha.test.exception.BadRequestException;
import ru.alpha.test.model.dto.Gif;
import ru.alpha.test.model.dto.ResponseGif;
import ru.alpha.test.service.api.GifApiService;

import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class GifServiceTest {

    private static final Double NOW_RATE = 64.33;

    private static final Double YEST_RATE = 64.22;

    private static final String CURRENCY = "RUB";

    private static final String WRONG_CURRENCY = "CCC";

    private static final String SOME_URL = "someurl.com";

    @MockBean
    private NowRateService nowRateService;

    @MockBean
    private YesterdayRateService yesterdayRateService;

    @MockBean
    private GifApiService gifApiService;

    @Autowired
    private TagService tagService;

    @Autowired
    private GifService gifService;


    @Test
    void getGifUrlTest() {
        when(nowRateService.getNowRate(anyString())).thenReturn(NOW_RATE);
        when(yesterdayRateService.getYesterdayRate(anyString())).thenReturn(YEST_RATE);
        String tag = tagService.getTag(nowRateService.getNowRate(CURRENCY)
                , yesterdayRateService.getYesterdayRate(CURRENCY));
        Gif gif = new Gif();
        gif.setEmbed_url(SOME_URL);
        ResponseGif responseGif = new ResponseGif();
        responseGif.setData(gif);
        when(gifApiService.getGif(anyString(), anyString())).thenReturn(ResponseEntity.ok(responseGif));
        Assertions.assertEquals(gifService.getGifUrl(CURRENCY), SOME_URL);
    }

}
