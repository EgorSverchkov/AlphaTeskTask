package ru.alpha.test.model.dto;

import lombok.Data;

@Data
public class ResponseGif {

    private Gif data;

    private Object meta;
}
