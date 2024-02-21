package ru.practicum.yandex.ingredient;

import io.qameta.allure.Step;
import ru.practicum.yandex.Client;

public class IngredientClient extends Client {

    private static final String INGREDIENTS_PATH = "/ingredients";

    @Step("Получение ингредиента")
    public String receiveIngredient (String ingredientType) {
        return spec()
                .when()
                .get(INGREDIENTS_PATH)
                .then()
                .extract()
                .jsonPath()
                .param("ingredientType", ingredientType)
                .getString("data.find { it.type == ingredientType }._id");
    }
}
