package ru.practicum.yandex.user;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class UserUnsuccessfulLoginWithIncorrectDataTest {

    private final UserClient client = new UserClient();
    private final UserAssertions check = new UserAssertions();
    private static final String existingEmail = UserGenerator.existing().getEmail();
    private static final String randomEmail = UserGenerator.random().getEmail();
    private static final String randomPass = UserGenerator.random().getPassword();
    private final Credentials creds;

    public UserUnsuccessfulLoginWithIncorrectDataTest(Credentials creds, String description) {
        this.creds = creds;
    }

    @Parameterized.Parameters(name = "{1}")
    public static Object[][] getResult() {
        return new Object[][] {
                { new Credentials(existingEmail, randomPass), "С неверным паролем" },
                { new Credentials(randomEmail, randomPass), "С несуществующем email" }
        };
    }

    @Test
    @DisplayName("Неуспешная авторизация с неверными данными")
    public void unsuccessfulLoginWithIncorrectData() {
        ValidatableResponse loginResponse = client.login(creds);
        check.gotErrorWhenLoginWithIncorrectData(loginResponse);
    }
}
