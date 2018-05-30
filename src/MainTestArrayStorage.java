import model.Resume;
import storage.AbstractArrayStorage;
import storage.ArrayStorage;
import storage.SortedArrayStorage;

/**
 * Test for com.urise.webapp.storage.storage.ArrayStorage
 */
public class MainTestArrayStorage {
    private static final AbstractArrayStorage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume();
        r1.setUuid("uuid12");
        Resume r2 = new Resume();
        r2.setUuid("uuid1");
        Resume r3 = new Resume();
        r3.setUuid("uuid18");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        printAll();
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();

        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    public static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
