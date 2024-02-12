package ru.practicum.yandex.user;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static java.net.HttpURLConnection.*;
import static org.hamcrest.CoreMatchers.*;
import static ru.practicum.yandex.utils.Constants.*;

public class UserAssertions {

    @Step("Проверка успешной регистрации")
    public void registeredSuccessfully (ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HTTP_OK)
                .body("success", is(true));
    }

    @Step("Проверка получения ошибки при регистрации с существующим email")
    public void gotErrorWhenRegisteringWithExistingEmail(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HTTP_FORBIDDEN)
                .body("message", equalTo(MSG_USER_ALREADY_EXISTS));
    }

    @Step("Проверка получения ошибки при регистрации без заполнения обязательных полей")
    public void gotErrorWhenRegisteringWithoutRequiredFields(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HTTP_FORBIDDEN)
                .body("message", equalTo(MSG_REQUIRED_FIELDS));
    }

    @Step("Проверка успешной авторизации")
    public String loggedInSuccessfully(ValidatableResponse response) {
        return response
                .assertThat()
                .statusCode(HTTP_OK)
                .body("accessToken", allOf(notNullValue(), not("")))
                .extract()
                .path("accessToken");
    }

    @Step("Проверка успешного удаления пользователя")
    public void deletedSuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HTTP_ACCEPTED)
                .body("message", equalTo(MSG_USER_SUCCESSFULLY_REMOVED));
    }

    @Step("Проверка получения ошибки при авторизации с неверными данными")
    public void gotErrorWhenLoginWithIncorrectData(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HTTP_UNAUTHORIZED)
                .body("message", equalTo(MSG_EMAIL_OR_PASSWORD_ARE_INCORRECT));
    }

    @Step("Проверка успешного редактирования данных")
    public void editedSuccessfully(ValidatableResponse response, User user) {
        response
                .assertThat()
                .statusCode(HTTP_OK)
                .body("user.name", equalTo(user.getName()));
    }

    @Step("Проверка получения ошибки при изменении данных без токена")
    public void gotErrorWhenEditingWithoutToken(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HTTP_UNAUTHORIZED)
                .body("message", equalTo(MSG_YOU_SHOULD_BE_AUTHORISED));
    }
}
