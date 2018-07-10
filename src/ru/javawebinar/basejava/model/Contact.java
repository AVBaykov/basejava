package ru.javawebinar.basejava.model;

public class Contact {
    private final ContactType type;

    public Contact(ContactType type) {
        this.type = type;
    }

    public ContactType getType() {
        return type;
    }
}
