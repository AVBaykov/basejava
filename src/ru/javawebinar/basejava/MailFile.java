package ru.javawebinar.basejava;

import java.io.File;
import java.util.Objects;

public class MailFile {
    public static void main(String[] args) {
        File dir = new File(System.getProperty("user.dir"));

        dirWalker(dir);
    }

    public static void dirWalker(File dir) {

        for (File file : Objects.requireNonNull(dir.listFiles())) {
            if (file.isDirectory()) {
                dirWalker(file);
            } else {
                System.out.println(file.getName());
            }
        }
    }
}
