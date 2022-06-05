package ru.alpha.test.service.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.alpha.test.model.dto.Gif;
import ru.alpha.test.model.dto.ResponseGif;

import java.util.List;
import java.util.Map;

@FeignClient(name = "${gif-service.name}", url = "${gif-service.base.url}")
public interface GifApiService {

    @GetMapping("/random?api_key={api_key}&tag={tag}")
    ResponseEntity<ResponseGif> getGif(@PathVariable("tag") String tag, @PathVariable("api_key") String api_key);

}
