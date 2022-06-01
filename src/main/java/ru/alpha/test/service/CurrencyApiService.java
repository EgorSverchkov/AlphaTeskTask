package ru.alpha.test.service;


import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.alpha.test.model.dto.RequestCurrenctApi;

@FeignClient(name = "${service.name}",url = "${service.base.url}")
public interface CurrencyApiService {
    @GetMapping("/historical/{date}.json?app_id=3c76b3ac8e774c51b1f6cbac021c1bf5")
    ResponseEntity<String> getCurrency(@PathVariable("date") String date);
}
