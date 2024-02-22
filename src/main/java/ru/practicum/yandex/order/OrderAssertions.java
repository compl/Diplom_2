package ru.practicum.yandex.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static java.net.HttpURLConnection.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.not;
import static ru.practicum.yandex.utils.Constants.*;

public class OrderAssertions {

    @Step("Проверка успешного получения заказов")
    public void receivedOrdersSuccessfully (ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HTTP_OK)
                .body("orders.id", allOf(notNullValue(), not("")));
    }

    @Step("Проверка ошибки при получении заказов без токена")
    public void gotErrorWhenReceivingOrdersWithoutToken(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HTTP_UNAUTHORIZED)
                .body("message", equalTo(MSG_YOU_SHOULD_BE_AUTHORISED));
    }

    @Step("Проверка успешного создания заказа")
    public void madeOrderSuccessfully (ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HTTP_OK)
                .body("order.number", allOf(notNullValue(), not("")));
    }

    @Step("Проверка получения ошибки при создании заказа без ингридиентов")
    public void gotErrorWhenMakingOrderWithoutIngredients(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HTTP_BAD_REQUEST)
                .body("message", equalTo(MSG_INGREDIENTS_ARE_REQUIRED));
    }

    @Step("Проверка получения ошибки при создании заказа с неверным ингридиентом")
    public void gotErrorWhenMakingOrderWithInvalidIngredient(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HTTP_INTERNAL_ERROR);
    }
}
