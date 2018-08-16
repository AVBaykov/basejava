package ru.javawebinar.basejava.util;

public class LazySingleton {
    volatile private static LazySingleton ourInstance;

    private LazySingleton() {
    }

    public static LazySingleton getInstance() {
        if (ourInstance == null) {
            synchronized (LazySingleton.class) {
                if (ourInstance == null) {
                    ourInstance = new LazySingleton();
                }
            }
        }
        return ourInstance;
    }
}
