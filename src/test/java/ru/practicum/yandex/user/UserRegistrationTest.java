package ru.practicum.yandex.user;

import io.restassured.response.ValidatableResponse;
import org.junit.Test;

public class UserRegistrationTest {

    private final UserClient client = new UserClient();
    private final UserAssertions check = new UserAssertions();
    private User user;

    @Test
    public void successfulUserRegistration() {
        user = UserGenerator.randomEmail();

        ValidatableResponse response = client.register(user);
        check.registeredSuccessfully(response);
    }
}