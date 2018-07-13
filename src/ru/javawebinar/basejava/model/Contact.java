package ru.javawebinar.basejava.model;

public abstract class Contact {
    private final ContactType type;
    private String title;

    Contact(ContactType type, String title) {
        this.type = type;
        this.title = title;
    }

    public ContactType getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
