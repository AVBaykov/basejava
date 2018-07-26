package ru.javawebinar.basejava;

import java.io.File;
import java.util.Objects;

public class MailFile {
    public static void main(String[] args) {
        File dir = new File(System.getProperty("user.dir"));

        dirWalker(dir);
    }

    public static void dirWalker(File dir) {
        File[] files = dir.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    System.out.println("File: " + file.getName());
                } else if (file.isDirectory()) {
                    System.out.println("Directory: " + file.getName());
                    dirWalker(file);
                }
            }
        }
    }
}
