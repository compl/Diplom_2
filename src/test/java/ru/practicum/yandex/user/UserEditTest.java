package ru.practicum.yandex.user;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserEditTest {

    private final UserClient client = new UserClient();
    private final UserAssertions check = new UserAssertions();
    private User user;
    private Credentials creds;
    private final User editedData = UserGenerator.withoutSomeField("email");
    private ValidatableResponse response;
    private String token;

    @Before
    @DisplayName("Регистрация пользователя")
    public void registerAndLoginUser() {
        user = UserGenerator.random();
        response = client.register(user);
        check.registeredSuccessfully(response);

        creds = Credentials.from(user);
        response = client.login(creds);
        token = check.loggedInSuccessfully(response);
    }

    @After
    @DisplayName("Удаление пользователя из БД")
    public void deleteUser() {
        response = client.delete(token);
        check.deletedSuccessfully(response);
    }

    @Test
    @DisplayName("Изменение данных пользователя")
    public void successfulUserEdit() {
        response = client.edit(token, editedData);
        check.editedSuccessfully(response, editedData);
    }

    @Test
    @DisplayName("Неуспешное изменение данных неавторизованного пользователя")
    public void unsuccessfulUserEditWithoutToken() {
        response = client.edit("", editedData);
        check.gotErrorWhenEditingWithoutToken(response);
    }
}