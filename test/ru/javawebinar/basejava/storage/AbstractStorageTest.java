package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.*;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static ru.javawebinar.basejava.TestData.*;

public abstract class AbstractStorageTest {
    static final File STORAGE_DIR = Config.get().getStorageDir();

    Storage storage;


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
        newResume.setContact(ContactType.EMAIL, "gmail@gmail.com");
        newResume.setContact(ContactType.PHONE, "22222");
        newResume.setContact(ContactType.SKYPE, "skype");
        newResume.setSection(SectionType.OBJECTIVE, new TextSection("ObjectiveNew"));
        newResume.setSection(SectionType.PERSONAL, new TextSection("Newest Personal data"));
        newResume.setSection(SectionType.ACHIEVEMENT, new ParagraphSection("AchivmentNew", "Achivment12new", "Achivment13NEW"));
        newResume.setSection(SectionType.QUALIFICATIONS, new ParagraphSection("Java NEW", "SQL BIF", "JavaScript BUF" +
                ""));
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
        assertEquals(sortedResumes, list);
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