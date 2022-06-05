package ru.alpha.test.service;

import org.springframework.stereotype.Component;

@Component
public class TagServiceImpl implements TagService {

    private static final String RICH_TAG = "rich";

    public static final String BROKE_TAG = "broke";

    public static final String EQUALS_TAG = "equals";


    @Override
    public String getTag(Double currencyNow, Double currencyYesterday) {

        if (currencyNow > currencyYesterday) {
            return RICH_TAG;
        }

        if (currencyNow < currencyYesterday) {
            return BROKE_TAG;
        }

        return EQUALS_TAG;
    }
}
