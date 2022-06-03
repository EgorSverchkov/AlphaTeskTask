package ru.alpha.test.controller;


import org.apache.catalina.connector.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.alpha.test.exception.BadRequestException;
import ru.alpha.test.service.GifService;

@org.springframework.stereotype.Controller
public class Controller {

    private final GifService gifService;

    public Controller(GifService gifService) {
        this.gifService = gifService;
    }


    @GetMapping("/gif")
    @ExceptionHandler(BadRequestException.class)
    public String indexPage(@RequestParam String currency) {
        try {
            String gif = gifService.getGif(currency);
            return "redirect:" + gif;
        } catch (BadRequestException e) {
            return "error.html";
        }
    }

}
