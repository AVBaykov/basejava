package ru.javawebinar.basejava.model;

public abstract class AbstcartContact {
    private final ContactType type;

    public AbstcartContact(ContactType type) {
        this.type = type;
    }

    public ContactType getType() {
        return type;
    }
}
