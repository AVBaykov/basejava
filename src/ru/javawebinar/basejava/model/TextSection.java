package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

public class TextSection extends Section {
    private List<String> description = new ArrayList<>();

    TextSection(SectionType type) {
        super(type);
    }
}
