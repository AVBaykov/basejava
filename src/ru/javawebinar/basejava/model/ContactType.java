package ru.javawebinar.basejava.model;

public enum ContactType {
    ADDRESS("Адрес"),
    PHONE("Телефон"),
    EMAIL("e-mail") {
        @Override
        public String toHtml0(String value) {
            return getTitle() + ": " + toLink("mailto:" + value, value);
        }
    },
    SKYPE("Skype") {
        @Override
        public String toHtml0(String value) {
            return getTitle() + ": " + toLink("skype:" + value, value);
        }
    },
    VKPROFILE("Профиль ВК"),
    HOMEPAGE("Домашняя страничка") {
        @Override
        public String toHtml0(String value) {
            return toLink(value);
        }
    };


    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    protected String toHtml0(String value) {
        return title + ": " + value;
    }

    public String toHtml(String value) {
        return (value == null) ? "" : toHtml0(value);
    }

    public String toLink(String href) {
        return toLink(href, title);
    }

    public static String toLink(String href, String title) {
        return "<a href='" + href + "'>" + title + "</a>";
    }
}
