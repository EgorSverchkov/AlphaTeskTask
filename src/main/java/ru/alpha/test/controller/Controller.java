package ru.alpha.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import ru.alpha.test.service.GifService;

@org.springframework.stereotype.Controller
public class Controller {

    private final GifService gifService;

    public Controller(GifService gifService) {
        this.gifService = gifService;
    }


    @GetMapping("/api/v1/gif")
    public String indexPage() {
        String gif = gifService.getGif();
        return "redirect:" + gif;
    }

}
