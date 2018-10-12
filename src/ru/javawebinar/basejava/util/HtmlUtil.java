package ru.javawebinar.basejava.util;

import ru.javawebinar.basejava.model.*;

public class HtmlUtil {

    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    protected static String toHtml0(Section section) {
        StringBuilder sb = new StringBuilder();
        if (section instanceof TextSection) {
            return ((TextSection) section).getContent();
        } else if (section instanceof ParagraphSection) {
            ((ParagraphSection) section).getParagraphList().forEach(p -> sb.append("<li>").append(p).append("</li>"));
            return sb.toString();
        } else if (section instanceof PlaceSection) {
            return section.toString();
        } else return "";
    }

    public static String toHtml(Section section) {
        return (section == null) ? "" : toHtml0(section);
    }

    public static String parseDate(Place.Period period) {
        return DateUtil.format(period.getStartDate()) + "-" + DateUtil.format(period.getEndDate());
    }
}
