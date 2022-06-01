package ru.alpha.test.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.alpha.test.model.dto.ResponseCurrencyApi;


@FeignClient(name = "${currency-service.name}", url = "${currency-service.base.url}")
public interface CurrencyApiService {
    @GetMapping("/historical/{date}.json?app_id=3c76b3ac8e774c51b1f6cbac021c1bf5")
    ResponseCurrencyApi getCurrency(@PathVariable("date") String date);
}
