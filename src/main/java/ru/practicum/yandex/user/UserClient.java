package ru.practicum.yandex.user;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.practicum.yandex.Client;

public class UserClient extends Client {

    private static final String AUTH_PATH = "/auth";
    private static final String REGISTER_PATH = "/register";
    private static final String LOGIN_PATH = "/login";
    private static final String USER_PATH = "/user";

    @Step("Запрос на регистрацию пользователя")
    public ValidatableResponse register (User user) {
        return spec()
                .body(user)
                .when()
                .post(AUTH_PATH + REGISTER_PATH)
                .then().log().all();
    }

    @Step("Запрос на авторизацию пользователя")
    public ValidatableResponse login (Credentials creds) {
        return spec()
                .body(creds)
                .when()
                .post(AUTH_PATH + LOGIN_PATH)
                .then().log().all();
    }

    @Step("Запрос на удаление пользователя")
    public ValidatableResponse delete(String token) {
        return spec()
                .header("Authorization", token)
                .when()
                .delete(AUTH_PATH + USER_PATH)
                .then().log().all();
    }

    @Step("Запрос на изменение данных пользователя")
    public ValidatableResponse edit(String token, User editedData) {
        return spec()
                .header("Authorization", token)
                .body(editedData)
                .when()
                .patch(AUTH_PATH + USER_PATH)
                .then().log().all();
    }
}
