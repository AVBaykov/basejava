package ru.javawebinar.basejava.model;

public class Contact {
    private final ContactType type;
    private String title;
    private String content;

    public Contact(ContactType type, String title, String content) {
        this.type = type;
        this.title = title;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", title, content);
    }
}
