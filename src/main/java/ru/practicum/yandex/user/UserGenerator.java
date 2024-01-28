package ru.practicum.yandex.user;

import org.apache.commons.lang3.RandomStringUtils;

public class UserGenerator {

    private static String randomEmail;
    private static final String EXISTING_EMAIL = "johny@ya.ru";
    private static final String DEFAULT_PASSWORD = "123Qwe";
    private static final String DEFAULT_NAME = "Johny";

    public static User randomEmail() {
        randomEmail = String.format("%s@%s", RandomStringUtils.randomAlphanumeric(5,8), "ww.ru");
        return new User(randomEmail, DEFAULT_PASSWORD, DEFAULT_NAME);
    }
}
