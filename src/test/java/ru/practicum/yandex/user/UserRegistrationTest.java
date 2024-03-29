package ru.practicum.yandex.user;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;

public class UserRegistrationTest {

    private final UserClient client = new UserClient();
    private final UserAssertions check = new UserAssertions();
    private User user;
    private ValidatableResponse response;

    @After
    @DisplayName("Удаление пользователя из БД")
    public void loginAndDeleteUser() {
        Credentials creds = Credentials.from(user);
        response = client.login(creds);
        String token = check.loggedInSuccessfully(response);

        response = client.delete(token);
        check.deletedSuccessfully(response);
    }

    @Test
    @DisplayName("Регистрация пользователя")
    public void successfulUserRegistration() {
        user = UserGenerator.random();

        response = client.register(user);
        check.registeredSuccessfully(response);
    }

    @Test
    @DisplayName("Регистрация уже существующего пользователя")
    public void unsuccessfulUserRegistrationWithExistingEmail() {
        user = UserGenerator.random();

        response = client.register(user);
        check.registeredSuccessfully(response);

        response = client.register(user);
        check.gotErrorWhenRegisteringWithExistingEmail(response);
    }
}