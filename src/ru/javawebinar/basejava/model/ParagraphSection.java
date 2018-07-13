package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

public class ParagraphSection extends Section {
    private List<Paragraph> paragraphList = new ArrayList<>();


    public ParagraphSection(SectionType type) {
        super(type);
    }

    public void addParagraph() {
        paragraphList.add(new Paragraph());
    }

    public void addParagraph(String paragraphName) {
        paragraphList.add(new Paragraph(paragraphName));
    }

    public void addParagraphName(int paragraph, String paragraphName) {
        paragraphList.get(paragraph).paragraphName = paragraphName;
    }

    public void addDescription(int paragraph, String description) {
        paragraphList.get(paragraph).description.add(description);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        paragraphList.forEach(paragraph -> builder.append("\n").append(paragraph.toString()));
        return "\n" + getTitle() + builder.toString();
    }

    private class Paragraph {
        private String paragraphName = "";
        private List<String> description = new ArrayList<>();

        Paragraph() {
        }

        Paragraph(String paragraphName) {
            this.paragraphName = paragraphName + ":";
        }


        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            description.forEach(builder::append);
            return String.format("%s %s", paragraphName, builder);
        }
    }
}
