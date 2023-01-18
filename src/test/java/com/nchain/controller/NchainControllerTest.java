package com.nchain.controller;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.util.UriComponentsBuilder;

import static com.nchain.util.RestUtil.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NchainControllerTest {

    @Autowired
    private NchainAppController nchainAppController;
    @LocalServerPort
    private int port;

    @Test
    void testControllerIsNotNull() {
        assertThat(nchainAppController).isNotNull();
    }

    @Test
    void indexReturnsFirstPage() throws JSONException {
        String url = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port)
                .path("/nchain-app")
                .toUriString();

        assertGetEquals(url, readFile("json/get-first-page.json"), formatToJson);
    }

    @Test
    void index_sortedByImage_returnsValidValues() throws JSONException {
        String url = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port)
                .path("/nchain-app")
                .queryParam("sort", "image,desc")
                .toUriString();

        assertGetEquals(url, readFile("json/get-sort-by-image-desc.json"), formatToJson);
    }


    @Test
    void createNewNchainApp_returnsCreated() throws JSONException {
        String url = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port)
                .path("/nchain-app")
                .toUriString();
        assertPostEquals(url, "json/create-new-nchain-app.json", readFile("json/create-new-nchain-app-response.json"),
                formatToJsonArray);
    }

    @Test
    void getExisting_returnsOk() throws JSONException {
        String url = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port)
                .path("/nchain-app/1")
                .toUriString();

        assertGetEquals(url, readFile("json/get-1.json"), formatToJson);
    }

    @Test
    void getNonExisting_returns404() throws JSONException {
        String url = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port)
                .path("/nchain-app/100")
                .toUriString();

        assertGetStatus(url, 404);
    }

    @Test
    void search_findsValidRecords() throws JSONException {
        String url = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port)
                .path("/nchain-app/search")
                .queryParam("searchParams", "type:android,name:th")
                .toUriString();

        assertGetEquals(url, readFile("json/search-response.json"), formatToJson);
    }
}



