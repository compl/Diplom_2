package ru.practicum.yandex.user;

import io.restassured.response.ValidatableResponse;

import java.net.HttpURLConnection;

public class UserAssertions {

    public void registeredSuccessfully (ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK);
    }
}
