package ru.practicum.yandex.order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import ru.practicum.yandex.user.*;

public class OrderTest {

    private final UserClient userClient = new UserClient();
    private final OrderClient orderClient = new OrderClient();
    private final UserAssertions userCheck = new UserAssertions();
    private final OrderAssertions orderCheck = new OrderAssertions();
    private ValidatableResponse response;
    private String token;

    @Before
    @DisplayName("Авторизация пользователя")
    public void login() {
        User user = UserGenerator.existing();
        Credentials creds = Credentials.from(user);
        response = userClient.login(creds);
        token = userCheck.loggedInSuccessfully(response);
    }

    @Test
    @DisplayName("Получение заказов пользователя")
    public void successfulOrdersReceive() {
        response = orderClient.receiveOrders(token);
        orderCheck.receivedOrdersSuccessfully(response);
    }

    @Test
    @DisplayName("Получение заказов неавторизованного пользователя")
    public void unsuccessfulOrdersReceiveWithoutToken() {
        response = orderClient.receiveOrders("");
        orderCheck.gotErrorWhenReceivingOrdersWithoutToken(response);
    }

    @Test
    @DisplayName("Создание заказа")
    public void successfulOrderCreation() {
        Order order = OrderGenerator.make();
        response = orderClient.makeOrder(token, order);
        orderCheck.madeOrderSuccessfully(response);
    }

    @Test
    @DisplayName("Создание заказа неавторизованным пользователем")
    public void unsuccessfulOrderCreationWithoutToken() {
        Order order = OrderGenerator.make();
        response = orderClient.makeOrder("", order);
        orderCheck.madeOrderSuccessfully(response);
    }

    @Test
    @DisplayName("Создание заказа без ингридиентов")
    public void unsuccessfulOrderCreationWithoutIngredients() {
        Order order = OrderGenerator.makeWithoutIngredients();
        response = orderClient.makeOrder(token, order);
        orderCheck.gotErrorWhenMakingOrderWithoutIngredients(response);
    }

    @Test
    @DisplayName("Создание заказа с невалидным ингридиентом")
    public void unsuccessfulOrderCreationWithInvalidIngredient() {
        Order order = OrderGenerator.makeWithInvalidIngredient();
        response = orderClient.makeOrder(token, order);
        orderCheck.gotErrorWhenMakingOrderWithInvalidIngredient(response);
    }
}
