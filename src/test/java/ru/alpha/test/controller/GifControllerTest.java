package ru.alpha.test.controller;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.alpha.test.service.GifService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GifController.class)
public class GifControllerTest {

    private static final String SOME_URL = "someurl.com";

    private static final String CURRENCY = "RUB";


    @MockBean
    private GifService gifService;


    MockMvc mockMvc;

    @BeforeEach
    void setup(WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void getSomeUrlTest() throws Exception {
        when(gifService.getGifUrl(CURRENCY)).thenReturn(SOME_URL);

        this.mockMvc.perform(get("/api/v1/gif?currency={CURRENCY}", CURRENCY))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(SOME_URL));

    }
}
