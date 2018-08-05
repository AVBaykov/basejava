package ru.javawebinar.basejava.storage.strategies;

import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.util.XmlParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class XmlSerializer implements Serializer {
    private XmlParser xmlParser;

    public XmlSerializer() {
        this.xmlParser = new XmlParser(Resume.class, Place.class, Link.class, PlaceSection.class,
                TextSection.class, ParagraphSection.class, Place.Period.class);
    }

    @Override
    public void doWrite(OutputStream os, Resume resume) throws IOException {
        try (Writer w = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            xmlParser.marhall(resume, w);
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (Reader r = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            return xmlParser.unmarshall(r);
        }
    }
}
