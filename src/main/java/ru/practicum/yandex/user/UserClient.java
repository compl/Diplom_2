package ru.practicum.yandex.user;

import io.restassured.response.ValidatableResponse;
import ru.practicum.yandex.Client;

public class UserClient extends Client {

    private static final String AUTH_PATH = "/auth";
    private static final String REGISTER_PATH = "/register";

    public ValidatableResponse register (User user) {
        return spec()
                .body(user)
                .when()
                .post(AUTH_PATH + REGISTER_PATH)
                .then().log().all();
    }
}
