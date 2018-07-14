package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

public class ParagraphSection extends Section {
    private List<String> paragraphList = new ArrayList<>();


    public void addParagraph(String paragraph) {
        paragraphList.add(paragraph);
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        paragraphList.forEach(paragraph -> builder.append("\n").append(paragraph));
        return builder.toString();
    }
}
