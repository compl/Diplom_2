package ru.practicum.yandex.order;

import java.util.List;

public class OrderGenerator {

    public static final String BUN_ING_ID = "61c0c5a71d1f82001bdaaa6d";
    public static final String MAIN_ING_ID = "61c0c5a71d1f82001bdaaa6f";
    public static final String SAUCE_ING_ID = "61c0c5a71d1f82001bdaaa72";
    public static final String WRONG_INGREDIENT_ID = "61c0c5a71d1f110555d0";

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
