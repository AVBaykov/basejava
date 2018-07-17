package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class ParagraphSection extends Section {
    private final List<String> paragraphList;

    public ParagraphSection(List<String> paragraphList) {
        Objects.requireNonNull(paragraphList, "paragraphs must not be null");
        this.paragraphList = paragraphList;
    }

    public List<String> getParagraphList() {
        return paragraphList;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        paragraphList.forEach(paragraph -> builder.append("\n").append(paragraph));
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParagraphSection that = (ParagraphSection) o;

        return paragraphList.equals(that.paragraphList);
    }

    @Override
    public int hashCode() {
        return paragraphList.hashCode();
    }
}
