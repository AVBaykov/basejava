package ru.javawebinar.basejava.util;

import org.junit.Assert;
import org.junit.Test;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.model.Section;
import ru.javawebinar.basejava.model.TextSection;

import java.io.IOException;

import static ru.javawebinar.basejava.TestData.resume1;

public class JsonParserTest {

    @Test
    public void testResume() throws IOException {
        String json = JsonParser.write(resume1);
        System.out.println(json);
        Resume resume2 = JsonParser.read(json, Resume.class);
        Assert.assertEquals(resume1, resume2);
    }

    @Test
    public void write() {
        Section section1 = new TextSection("Objective1");
        String json = JsonParser.write(section1, Section.class);
        System.out.println(json);
        Section section2 = JsonParser.read(json, Section.class);
        Assert.assertEquals(section1, section2);
    }
}