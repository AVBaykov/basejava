package ru.javawebinar.basejava.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ParagraphSection extends Section {
    private static final long serialVersionUID = 1L;

    private List<String> paragraphList;

    public ParagraphSection() {
    }

    public ParagraphSection(String... paragraphs) {
        this(Arrays.asList(paragraphs));
    }

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
            paragraphList.forEach(paragraph -> builder.append(paragraph).append(" "));
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
