package ru.practicum.yandex.order;

import ru.practicum.yandex.ingredient.IngredientClient;

import java.util.List;

public class OrderGenerator {
    private static IngredientClient client = new IngredientClient();

    private static final String BUN_ING_ID = client.receiveIngredient("bun");
    private static final String MAIN_ING_ID = client.receiveIngredient("main");
    private static final String SAUCE_ING_ID = client.receiveIngredient("sauce");
    private static final String WRONG_INGREDIENT_ID = "61c0c5a71d1f110555d0";

    public static Order make() {
        return new Order(List.of(BUN_ING_ID, MAIN_ING_ID, SAUCE_ING_ID));
    }

    public static Order makeWithoutIngredients() {
        return new Order(List.of());
    }

    public static Order makeWithInvalidIngredient() {
        return new Order(List.of(WRONG_INGREDIENT_ID));
    }
}
