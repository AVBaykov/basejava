package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    private Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private final Resume resume1 = new Resume(UUID_1);
    private final Resume resume2 = new Resume(UUID_2);
    private final Resume resume3 = new Resume(UUID_3);
    private final Resume resume4 = new Resume(UUID_4);

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        storage.update(resume2);
        assertSame(storage.get(UUID_2), resume2);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume("dummy"));
    }

    @Test
    public void save() {
        storage.save(resume4);
        Resume[] actual = {resume1, resume2, resume3, resume4};

        assertArrayEquals(storage.getAll(), actual);
        assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(resume1);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        storage.clear();
        try {
            for (int i = 0; i < 10000; i++) {
                storage.save(new Resume(String.valueOf(i)));
            }
        } catch (StorageException e) {
            fail("Storage overflow before storage limit");
        }
        storage.save(new Resume("10001"));
    }

    @Test
    public void get() {
        assertEquals(storage.get(UUID_2), resume2);
    }

    @Test
    public void getAll() {
        Resume[] actual = {resume1, resume2, resume3};

        assertArrayEquals(storage.getAll(), actual);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void delete() {
        storage.delete(UUID_2);
        Resume[] actual = {resume1, resume3};

        assertArrayEquals(storage.getAll(), actual);
        assertEquals(2, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.update(new Resume("dummy"));
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }
}