package ru.alpha.test.model.dto;

import lombok.Data;

import java.util.Map;

@Data
public class ResponseCurrencyApi {
    private String disclaimer;

    private String license;

    private Integer timestamp;

    private String base;

    private Map<String, Double> rates;
}
