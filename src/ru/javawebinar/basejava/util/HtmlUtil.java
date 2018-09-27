package ru.javawebinar.basejava.util;

import ru.javawebinar.basejava.model.ParagraphSection;
import ru.javawebinar.basejava.model.Section;
import ru.javawebinar.basejava.model.TextSection;

public class HtmlUtil {

    protected static String toHtml0(Section section) {
        if (section instanceof TextSection) {
            return ((TextSection) section).getContent();
        } else if (section instanceof ParagraphSection) {
            StringBuilder sb = new StringBuilder();
            ((ParagraphSection) section).getParagraphList().forEach(p -> sb.append("<li>").append(p).append("</li>"));
            return sb.toString();
        } else return "";
    }

    public static String toHtml(Section section) {
        return (section == null) ? "" : toHtml0(section);
    }
}
