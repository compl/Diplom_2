package ru.practicum.yandex.user;

import org.apache.commons.lang3.RandomStringUtils;

public class UserGenerator {

    private static String randomEmail;
    private static String randomPassword;
    private static String randomName;
    private static final String EXISTING_EMAIL = "johny@ya.ru";
    private static final String DEFAULT_PASSWORD = "123Qwe";
    private static final String DEFAULT_NAME = "Johny";

    public static User random() {
        randomEmail = String.format("%s@%s", RandomStringUtils.randomAlphanumeric(5,8), "ww.ru");
        randomPassword = RandomStringUtils.randomAlphanumeric(5,10);
        randomName = RandomStringUtils.randomAlphabetic(7);
        return new User(randomEmail, randomPassword, randomName);
    }

    public static User existing() {
        return new User(EXISTING_EMAIL, DEFAULT_PASSWORD, DEFAULT_NAME);
    }

    public static User withoutSomeField(String field) {
        randomEmail = String.format("%s@%s", RandomStringUtils.randomAlphanumeric(5,8), "ww.ru");
        randomPassword = RandomStringUtils.randomAlphanumeric(5,10);
        randomName = RandomStringUtils.randomAlphabetic(7);

        switch (field) {
            case "email":
                return new User(null, randomPassword, randomName);
            case "password":
                return new User(randomEmail, null, randomName);
            case "name":
                return new User(randomEmail, randomPassword, null);
            default:
                throw new IllegalArgumentException("Unexpected field: " + field);
        }
    }
}
