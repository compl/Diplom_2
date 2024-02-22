package ru.practicum.yandex.user;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class UserUnsuccessfulRegistrationTest {

    private final UserClient client = new UserClient();
    private final UserAssertions check = new UserAssertions();

    private final String field;

    public UserUnsuccessfulRegistrationTest(String field, String description) {
        this.field = field;
    }

    @Parameterized.Parameters(name = "{1}")
    public static Object[][] getResult() {
        return new Object[][] {
                { "email", "Без email" },
                { "password", "Без пароля" },
                { "name", "Без имени" }
        };
    }

    @Test
    @DisplayName("Неуспешное создание пользователя без обязательных полей")
    public void unsuccessfulUserCreation() {
        User user = UserGenerator.withoutSomeField(field);

        ValidatableResponse response = client.register(user);
        check.gotErrorWhenRegisteringWithoutRequiredFields(response);
    }
}