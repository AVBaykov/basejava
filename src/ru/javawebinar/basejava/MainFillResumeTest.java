package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;

public class MainFillResumeTest {

    public static void main(String[] args) {
        Resume resume = new Resume("uuid1", "John doe");
        PlaceSection experience = new PlaceSection(SectionType.EXPERIENCE);
        String workPlace = "Рога и копыта";
        experience.addPlace(workPlace);
        experience.addStartDate(workPlace, LocalDate.of(2012, 1, 15));
        experience.addEndDate(workPlace, LocalDate.of(2014, 5, 20));
        experience.addPosition(workPlace, "Решала");
        experience.addDescription(workPlace, "Решал проблемы. Делал грязь.");

        workPlace = "ОАО Цой жив";
        experience.addPlace(workPlace);
        experience.addStartDate(workPlace, LocalDate.of(2014, 6, 15));
        experience.addEndDate(workPlace, LocalDate.of(2016, 5, 20));
        experience.addPosition(workPlace, "Главный фанат");
        experience.addDescription(workPlace, "Следил за собой и был осторожен. Сажал алюминиевые огурцы.");
        experience.addDescription(workPlace, "Слушал стук в дверь. Уходя, оборачивался на пороге.");
        resume.addSection(SectionType.EXPERIENCE, experience);

        PlaceSection education = new PlaceSection(SectionType.EDUCATION);
        String educationPlace = "Мичиганский университет самых востребованных специальностей";
        education.addPlace(educationPlace);
        education.addStartDate(educationPlace, LocalDate.of(2000, 9, 1));
        education.addEndDate(educationPlace, LocalDate.of(2015, 6, 30));
        education.addDescription(educationPlace, "закончил с дипломом");
        resume.addSection(SectionType.EDUCATION, education);

        TextSection personalSection = new TextSection(SectionType.PERSONAL);
        personalSection.addDescription("умен, красив, силен, покладист");
        TextSection positionSection = new TextSection(SectionType.OBJECTIVE);
        positionSection.addDescription("Одинокий бродяга любви Казанова");
        resume.addSection(SectionType.PERSONAL, personalSection);
        resume.addSection(SectionType.OBJECTIVE, positionSection);


        ParagraphSection achievement = new ParagraphSection(SectionType.ACHIEVEMENT);
        achievement.addParagraph();
        achievement.addDescription(0, "Сомнительные достижения в области экономики");
        achievement.addParagraph();
        achievement.addDescription(1, "Получил оскара");

        resume.addSection(SectionType.ACHIEVEMENT, achievement);

        ParagraphSection qualifications = new ParagraphSection(SectionType.QUALIFICATIONS);
        qualifications.addParagraph("Карате");
        qualifications.addDescription(0, "Черный пояс");
        qualifications.addParagraph("Спортивная езда на санках");
        qualifications.addDescription(1, "Уиииииии");

        resume.addSection(SectionType.QUALIFICATIONS, qualifications);

        Contact phone = new Contact(ContactType.PHONE, "Телефон", "+7 999 999 55 44");
        Contact address = new Contact(ContactType.ADDRESS, "Адрес", "улица Пушкина, дом Колотушкина");
        Contact skype = new Contact(ContactType.MESSENGER, "Skype", "skype");
        Contact email = new Contact(ContactType.EMAIL, "e-mail", "businka@mail.ru");
        resume.addContact(phone);
        resume.addContact(address);
        resume.addContact(skype);
        resume.addContact(email);


        System.out.println(resume.getFullName());
        resume.getContacts().forEach(System.out::println);
        resume.getSections().forEach(System.out::println);
    }

}
