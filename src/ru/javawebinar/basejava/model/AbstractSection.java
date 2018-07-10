package ru.javawebinar.basejava.model;

public abstract class AbstractSection {
    private final SectionType type;
    private final String title;

    AbstractSection(SectionType type){
        this.type = type;
        this.title = type.getTitle();
    }

    public SectionType getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }
}
