package ru.alpha.test.service;

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

    @GetMapping("/random?api_key=f0o1Mb2RD9YBtOzWtt7ZYi4Fvk9t95oQ&tag={tag}")
    ResponseEntity<ResponseGif> getGif(@PathVariable("tag") String tag);

}
