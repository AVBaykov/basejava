package ru.javawebinar.basejava.model;

public enum ContactType {
    ADDRESS("Адрес"),
    PHONE("Телефон"),
    EMAIL("e-mail"),
    SKYPE("Skype"),
    VKPROFILE("Профиль ВК"),
    HOMEPAGE("Домашняя страничка");


    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
