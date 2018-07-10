package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

public class ParagraphSection extends AbstractSection {
    private List<Paragraph> paragraphList = new ArrayList<>();

    ParagraphSection(SectionType type) {
        super(type);
    }

    private class Paragraph {
        private String paragraphName;
        private List<String> description = new ArrayList<>();
    }
}
