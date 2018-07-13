package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

public class TextSection extends Section {
    private List<String> descriptionList = new ArrayList<>();

    public TextSection(SectionType type) {
        super(type);
    }

    public void addDescription(String description) {
        descriptionList.add(description);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n").append(getTitle()).append("\n");
        descriptionList.forEach(string -> builder.append(string).append(" "));
        return builder.toString();
    }
}
