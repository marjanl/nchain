package com.nchain.controller;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.util.UriComponentsBuilder;

import static com.nchain.util.RestUtil.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ScoresTest {

    @LocalServerPort
    private int port;

    @Test
    void addScore_invalidScoreValue_returnsHttp400() throws JSONException {
        String url = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port)
                .path("/nchain-app/score")
                .toUriString();

        assertPostStatus(url,"json/add-score-invalid.json", 400);
    }

    @Test
    void addScore_validScoreValue_returnsCreatedAppDetail() throws JSONException {
        String url = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port)
                .path("/nchain-app/score")
                .toUriString();

        assertPostEquals(url,"json/add-score-valid.json", readFile("json/add-score-valid-response.json"), formatToJson);
    }
}
