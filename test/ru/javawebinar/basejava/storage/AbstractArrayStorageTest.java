package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    private Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

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

        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        Resume resume = new Resume(UUID_2);
        storage.update(resume);

        Assert.assertSame(storage.get(UUID_2), resume);
    }

    @Test
    public void save() {
        storage.save(new Resume(UUID_4));
        Resume[] actual = {new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3), new Resume(UUID_4)};

        Assert.assertArrayEquals(storage.getAll(), actual);
        Assert.assertEquals(4, storage.size());
    }

    @Test
    public void get() {
        Assert.assertEquals(storage.get(UUID_2), new Resume(UUID_2));
    }

    @Test
    public void delete() {
        storage.delete(UUID_2);
        Resume[] actual = {new Resume(UUID_1), new Resume(UUID_3)};

        Assert.assertArrayEquals(storage.getAll(), actual);
        Assert.assertEquals(2, storage.size());
    }

    @Test
    public void getAll() {
        Resume[] actual = {new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3)};

        Assert.assertArrayEquals(storage.getAll(), actual);
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume("dummy"));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume(UUID_1));
    }

    @Test(expected = StorageException.class)
    public void overflow() {
        storage.clear();
        for (int i = 0; i < 10001; i++) {
            storage.save(new Resume(String.valueOf(i)));
        }
    }
}