package ru.alpha.test.service;

import org.springframework.http.ResponseEntity;
import ru.alpha.test.model.dto.Gif;
import ru.alpha.test.model.dto.ResponseGif;

import java.util.Map;

public interface GifService {
    String getGif();
}
