package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.util.DateUtil;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public abstract class AbstractStorageTest {

    Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String NAME_1 = "D";
    private static final String UUID_2 = "uuid2";
    private static final String NAME_2 = "C";
    private static final String UUID_3 = "uuid3";
    private static final String NAME_3 = "B";
    private static final String UUID_4 = "uuid4";
    private static final String NAME_4 = "A";
    private final Resume resume1 = new Resume(UUID_1, NAME_1);
    private final Resume resume2 = new Resume(UUID_2, NAME_2);
    private final Resume resume3 = new Resume(UUID_3, NAME_3);
    private final Resume resume4 = new Resume(UUID_4, NAME_4);

    AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1, NAME_1));
        storage.save(new Resume(UUID_2, NAME_2));
        storage.save(new Resume(UUID_3, NAME_3));

        resume2.addContact(ContactType.PHONE, "телефон");
        resume2.addContact(ContactType.SKYPE, "skype");
        resume2.addContact(ContactType.EMAIL, "e-mail");
        resume2.addSection(SectionType.PERSONAL, new TextSection("В меру упитанный мужчина"));
        resume2.addSection(SectionType.OBJECTIVE, new TextSection("Карлсон"));
        List<String> achievements = new ArrayList<>();
        achievements.add("умею летать");
        achievements.add("шучу, я не умею летать");
        resume2.addSection(SectionType.ACHIEVEMENT, new ParagraphSection(achievements));
        List<String> qualifications = new ArrayList<>();
        qualifications.add("супер бизон");
        qualifications.add("сомнительные достижения");
        resume2.addSection(SectionType.QUALIFICATIONS, new ParagraphSection(qualifications));
        LocalDate startDate = DateUtil.of(2014, Month.JANUARY);
        LocalDate endDate = DateUtil.of(2016, Month.APRIL);
        PlaceSection workPlace = new PlaceSection();
        workPlace.addPlace("Рога и копыта", "URL", startDate, endDate, "стажер", "учился");
        startDate = DateUtil.of(2016, Month.APRIL);
        endDate = DateUtil.of(2018, Month.JANUARY);
        workPlace.addPlace("Рога и копыта", "URL", startDate, endDate, "инженер", "работал");
        resume2.addSection(SectionType.EXPERIENCE, workPlace);
        startDate = DateUtil.of(2009, Month.SEPTEMBER);
        endDate = DateUtil.of(2014, Month.JULY);
        PlaceSection university = new PlaceSection();
        university.addPlace("Университет", null, startDate, endDate, "Инженер по рогам и копытам", null);
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        storage.update(resume2);
        assertSame(resume2, storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(resume4);
    }

    @Test
    public void save() {
        storage.save(resume4);
        assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(resume1);
    }


    @Test
    public void get() {
        assertEquals(resume2, storage.get(UUID_2));
    }

    @Test
    public void getAllSorted() {
        Resume[] actual = {resume3, resume2, resume1};

        assertArrayEquals(actual, storage.getAllSorted().toArray());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_4);
    }

    @Test
    public void delete() {
        storage.delete(UUID_2);
        assertEquals(2, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_4);
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }

}