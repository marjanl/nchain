package com.nchain.util;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.io.IOUtils;
import org.apache.http.entity.ContentType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestUtil {

    public static void assertPostEquals(String url, String inputJsonFilePath, String expectedResponse, Function<String, String> formater) throws JSONException {
        String actualResponse = RestAssured.given()
                .contentType(String.valueOf(ContentType.APPLICATION_JSON))
                .header("Accept", String.valueOf(ContentType.APPLICATION_JSON))
                .when()
                .body(readFile(inputJsonFilePath))
                .post(url)
                .then()
                .statusCode(200)
                .extract().asString();

        assertEquals(expectedResponse, formater.apply(actualResponse));
    }

    public static void assertPostStatus(String url, String inputJsonFilePath, int statusCode) throws JSONException {
        RestAssured.given()
                .contentType(String.valueOf(ContentType.APPLICATION_JSON))
                .header("Accept", String.valueOf(ContentType.APPLICATION_JSON))
                .when()
                .body(readFile(inputJsonFilePath))
                .post(url)
                .then()
                .statusCode(statusCode);
    }

    public static void assertGetStatus(String url, int statusCode) throws JSONException {
        RestAssured.given()
                .contentType(String.valueOf(ContentType.APPLICATION_JSON))
                .header("Accept", String.valueOf(ContentType.APPLICATION_JSON))
                .when()
                .get(url)
                .then()
                .statusCode(statusCode);
    }

    public static void assertGetEquals(String url, String expectedResponse, Function<String, String> formater) throws JSONException {
        String actualResponse = RestAssured.given()
                .contentType(String.valueOf(ContentType.APPLICATION_FORM_URLENCODED))
                .header("Accept", String.valueOf(ContentType.APPLICATION_JSON))
                .when()
                .get(url)
                .then()
                .statusCode(200)
                .extract().asString();

        assertEquals(expectedResponse, formater.apply(actualResponse));
    }

    public static String readFile(String relativeFilePath) {
        InputStream fileInputStream = RestUtil.class.getClassLoader()
                .getResourceAsStream(relativeFilePath);

        return readStream(fileInputStream);
    }

    private static String readStream(InputStream fileInputStream) {
        try {
            return IOUtils.toString(fileInputStream, "UTF8");
        } catch (IOException e) {
            throw new IllegalStateException("Error reading the file input stream", e);
        }
    }

    public static Function<String, String> formatToJson = (var input) -> {
        try {
            JSONObject jsonObject = new JSONObject(input);
            return jsonObject.toString(4);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    };

    public static Function<String, String> formatToJsonArray = (var input) -> {
        try {
            JSONArray jsonObject = new JSONArray(input);
            return jsonObject.toString(4);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    };

}
