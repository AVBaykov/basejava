package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public abstract class AbstractStorageTest {
    static final File STORAGE_DIR = Config.get().getStorageDir();

    Storage storage;

    private static final String UUID_1 = UUID.randomUUID().toString();
    private static final String UUID_2 = UUID.randomUUID().toString();
    private static final String UUID_3 = UUID.randomUUID().toString();
    private static final String UUID_4 = UUID.randomUUID().toString();

    private static final Resume resume1;
    private static final Resume resume2;
    private static final Resume resume3;
    private static final Resume resume4;

    static {
        resume1 = new Resume(UUID_1, "Name1");
        resume2 = new Resume(UUID_2, "Name2");
        resume3 = new Resume(UUID_3, "Name3");
        resume4 = new Resume(UUID_4, "Name4");

        resume1.addContact(ContactType.EMAIL, "mail1@ya.ru");
        resume1.addContact(ContactType.PHONE, "11111");
//        resume1.addSection(SectionType.OBJECTIVE, new TextSection("Objective1"));
//        resume1.addSection(SectionType.PERSONAL, new TextSection("Personal data"));
//        resume1.addSection(SectionType.ACHIEVEMENT, new ParagraphSection("Achivment11", "Achivment12", "Achivment13"));
//        resume1.addSection(SectionType.QUALIFICATIONS, new ParagraphSection("Java", "SQL", "JavaScript"));
//        resume1.addSection(SectionType.EXPERIENCE,
//                new PlaceSection(
//                        new Place("Place11", "http://Place11.ru",
//                                new Place.Period(2005, Month.JANUARY, "Period1", "content1"),
//                                new Place.Period(2001, Month.MARCH, 2005, Month.JANUARY, "Period2", "content2"))));
//        resume1.addSection(SectionType.EDUCATION,
//                new PlaceSection(
//                        new Place("Institute", null,
//                                new Place.Period(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
//                                new Place.Period(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet")),
//                        new Place("Place12", "http://Place12.ru")));
        resume2.addContact(ContactType.SKYPE, "skype2");
        resume2.addContact(ContactType.PHONE, "22222");
//        resume2.addSection(SectionType.EXPERIENCE,
//                new PlaceSection(
//                        new Place("Place2", "http://Place2.ru",
//                                new Place.Period(2015, Month.JANUARY, "Period1", "content1"))));
    }


    AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(resume3);
        storage.save(resume1);
        storage.save(resume2);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID_1, "New Name");
        newResume.addContact(ContactType.EMAIL, "gmail@gmail.com");
        newResume.addContact(ContactType.PHONE, "22222");
        newResume.addContact(ContactType.SKYPE, "skype");
        storage.update(newResume);
        assertEquals(newResume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(resume4);
    }

    @Test
    public void save() {
        storage.save(resume4);
        assertSize(4);
        assertGet(resume4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(resume1);
    }


    @Test
    public void get() {
        assertGet(resume1);
        assertGet(resume2);
        assertGet(resume3);
    }

    @Test
    public void getAllSorted() {
        List<Resume> list = storage.getAllSorted();
        assertEquals(3, list.size());
        List<Resume> sortedResumes = Arrays.asList(resume1, resume2, resume3);
        sortedResumes.sort(Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid));
        assertEquals(list, sortedResumes);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_4);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        try {
            storage.delete(UUID_1);
        } catch (NotExistStorageException e) {
            fail("Trying to delete nonexistence resume");
        }
        assertSize(2);
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_4);
    }

    @Test
    public void size() {
        assertSize(3);
    }

    private void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }

}