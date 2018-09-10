package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.Month;
import java.util.UUID;

public class TestData {
    public static final String UUID_1 = UUID.randomUUID().toString();
    public static final String UUID_2 = UUID.randomUUID().toString();
    public static final String UUID_3 = UUID.randomUUID().toString();
    public static final String UUID_4 = UUID.randomUUID().toString();

    public static final Resume resume1;
    public static final Resume resume2;
    public static final Resume resume3;
    public static final Resume resume4;

    static {
        resume1 = new Resume(UUID_1, "Name1");
        resume2 = new Resume(UUID_2, "Name2");
        resume3 = new Resume(UUID_3, "Name3");
        resume4 = new Resume(UUID_4, "Name4");

        resume1.addContact(ContactType.EMAIL, "mail1@ya.ru");
        resume1.addContact(ContactType.PHONE, "11111");
        resume1.addSection(SectionType.OBJECTIVE, new TextSection("Objective1"));
        resume1.addSection(SectionType.PERSONAL, new TextSection("Personal data"));
        resume1.addSection(SectionType.ACHIEVEMENT, new ParagraphSection("Achivment11", "Achivment12", "Achivment13"));
        resume1.addSection(SectionType.QUALIFICATIONS, new ParagraphSection("Java", "SQL", "JavaScript"));
        resume1.addSection(SectionType.EXPERIENCE,
                new PlaceSection(
                        new Place("Place11", "http://Place11.ru",
                                new Place.Period(2005, Month.JANUARY, "Period1", "content1"),
                                new Place.Period(2001, Month.MARCH, 2005, Month.JANUARY, "Period2", "content2"))));
        resume1.addSection(SectionType.EDUCATION,
                new PlaceSection(
                        new Place("Institute", null,
                                new Place.Period(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
                                new Place.Period(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet")),
                        new Place("Place12", "http://Place12.ru")));
        resume2.addContact(ContactType.SKYPE, "skype2");
        resume2.addContact(ContactType.PHONE, "22222");
        resume2.addSection(SectionType.EXPERIENCE,
                new PlaceSection(
                        new Place("Place2", "http://Place2.ru",
                                new Place.Period(2015, Month.JANUARY, "Period1", "content1"))));
    }
}
