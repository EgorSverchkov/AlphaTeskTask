package ru.alpha.test.service.api;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.alpha.test.model.dto.ResponseCurrencyApi;


@FeignClient(name = "${currency-service.name}", url = "${currency-service.base.url}")
public interface CurrencyApiService {
    @GetMapping("/historical/{date}.json?app_id={app_id}")
    ResponseCurrencyApi getCurrency(@PathVariable("date") String date,@PathVariable("app_id") String app_id);
}
