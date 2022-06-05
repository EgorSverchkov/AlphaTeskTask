package ru.alpha.test.controller;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.alpha.test.exception.BadRequestException;
import ru.alpha.test.service.GifService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;

@RestController
public class GifController {

    private final GifService gifService;

    public GifController(GifService gifService) {
        this.gifService = gifService;
    }


    private final String GET_GIF = "/api/v1/gif";

    @GetMapping(GET_GIF)
    public ResponseEntity<String> getGif(@RequestParam String currency) {
        try {
            String gif = gifService.getGifUrl(currency);
            return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(gif)).build();
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This currency is not listed or does not exist. Try again.");
        }
    }

}
