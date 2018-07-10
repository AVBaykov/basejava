package ru.javawebinar.basejava.model;

public abstract class Contact {
    private final ContactType type;

    public Contact(ContactType type) {
        this.type = type;
    }

    public ContactType getType() {
        return type;
    }
}
