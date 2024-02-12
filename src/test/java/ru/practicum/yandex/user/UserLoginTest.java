package ru.practicum.yandex.user;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

public class UserLoginTest {

    private final UserClient client = new UserClient();
    private final UserAssertions check = new UserAssertions();

    @Test
    @DisplayName("Авторизация существующего пользователя")
    public void successfulUserLogin() {
        User user = UserGenerator.existing();
        Credentials creds = Credentials.from(user);

        ValidatableResponse response = client.login(creds);
        check.loggedInSuccessfully(response);
    }
}