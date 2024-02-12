package ru.practicum.yandex.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.practicum.yandex.Client;

public class OrderClient extends Client {

    private static final String ORDERS_PATH = "/orders";

    @Step("Запрос на получение заказов")
    public ValidatableResponse receiveOrders (String token) {
        return spec()
                .header("Authorization", token)
                .when()
                .get(ORDERS_PATH)
                .then().log().all();
    }

    @Step("Запрос на создание заказа")
    public ValidatableResponse makeOrder (String token, Order order) {
        return spec()
                .header("Authorization", token)
                .body(order)
                .when()
                .post(ORDERS_PATH)
                .then().log().all();
    }
}
