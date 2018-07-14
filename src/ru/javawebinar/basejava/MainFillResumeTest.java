package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.util.Map;

public class MainFillResumeTest {

    public static void main(String[] args) {
        Resume resume = new Resume("uuid1", "John doe");
        PlaceSection experience = new PlaceSection();
        experience.addPlace("Рога и копыта");
        experience.addStartDate(0, LocalDate.of(2012, 1, 15));
        experience.addEndDate(0, LocalDate.of(2014, 5, 20));
        experience.addPosition(0, "Решала");
        experience.addDescription(0, "Решал проблемы. Делал грязь.");

        experience.addPlace("ОАО Цой жив");
        experience.addStartDate(1, LocalDate.of(2014, 6, 15));
        experience.addEndDate(1, LocalDate.of(2016, 5, 20));
        experience.addPosition(1, "Главный фанат");
        experience.addDescription(1, "Следил за собой и был осторожен. Сажал алюминиевые огурцы.");
        resume.addSection(SectionType.EXPERIENCE, experience);

        PlaceSection education = new PlaceSection();
        String educationPlace = "Мичиганский университет самых востребованных специальностей";
        education.addPlace(educationPlace);
        education.addStartDate(0, LocalDate.of(2000, 9, 1));
        education.addEndDate(0, LocalDate.of(2015, 6, 30));
        education.addDescription(0, "закончил с дипломом");
        resume.addSection(SectionType.EDUCATION, education);

        TextSection personalSection = new TextSection("умен, красив, силен, покладист");
        TextSection positionSection = new TextSection("Одинокий бродяга любви Казанова");
        resume.addSection(SectionType.PERSONAL, personalSection);
        resume.addSection(SectionType.OBJECTIVE, positionSection);


        ParagraphSection achievement = new ParagraphSection();
        achievement.addParagraph("Сомнительные достижения в области экономики");
        achievement.addParagraph("Получил оскара");

        resume.addSection(SectionType.ACHIEVEMENT, achievement);

        ParagraphSection qualifications = new ParagraphSection();
        qualifications.addParagraph("Карате: Черный пояс");
        qualifications.addParagraph("Спортивная езда на санках: Уиииииии");

        resume.addSection(SectionType.QUALIFICATIONS, qualifications);

        Contact phone = new Contact("+7 999 999 55 44");
        Contact address = new Contact("улица Пушкина, дом Колотушкина");
        Contact skype = new Contact("skype");
        Contact email = new Contact("businka@mail.ru");
        resume.addContact(ContactType.PHONE, phone);
        resume.addContact(ContactType.ADDRESS, address);
        resume.addContact(ContactType.SKYPE, skype);
        resume.addContact(ContactType.EMAIL, email);


        System.out.println(resume.getFullName());
        for (Map.Entry<ContactType, Contact> entry : resume.getContacts().entrySet()) {
            System.out.println(String.format("%s: %s", entry.getKey().getTitle(), entry.getValue()));
        }
        System.out.println();
        for (Map.Entry<SectionType, Section> entry : resume.getSections().entrySet()) {
            System.out.println();
            System.out.println(entry.getKey().getTitle());
            System.out.println(entry.getValue());
        }
    }

}
