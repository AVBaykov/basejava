package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DataSerializer implements Serializer {
    @Override
    public void doWrite(OutputStream os, Resume resume) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());

            writeCollection(dos, resume.getContacts().entrySet(), entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });

            writeCollection(dos, resume.getSections().entrySet(), entry -> {
                SectionType type = entry.getKey();
                Section section = entry.getValue();
                dos.writeUTF(type.name());
                switch (type) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(((TextSection) section).getContent());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        writeCollection(dos, ((ParagraphSection) section).getParagraphList(), dos::writeUTF);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        writeCollection(dos, ((PlaceSection) section).getPlaces(), place -> {
                            writeLink(dos, place);
                            writeCollection(dos, place.getPeriodList(), period -> writePeriod(dos, period));
                        });
                }
            });
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);

            readItems(dis, () -> resume.setContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));

            readItems(dis, () -> {
                SectionType type = SectionType.valueOf(dis.readUTF());
                resume.setSection(type, readSection(dis, type));
            });

            return resume;
        }
    }

    private Section readSection(DataInputStream dis, SectionType type) throws IOException {
        switch (type) {
            case PERSONAL:
            case OBJECTIVE:
                return new TextSection(dis.readUTF());
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                return new ParagraphSection(readList(dis, dis::readUTF));
            case EXPERIENCE:
            case EDUCATION:
                return new PlaceSection(
                        readList(dis, () -> new Place(
                                new Link(dis.readUTF(), dis.readUTF()),
                                readList(dis, () -> new Place.Period(
                                        LocalDate.parse(dis.readUTF()),
                                        LocalDate.parse(dis.readUTF()),
                                        dis.readUTF(),
                                        dis.readUTF()
                                ))
                        ))
                );
            default:
                throw new IllegalStateException();
        }
    }

    private <T> List<T> readList(DataInputStream dis, ElementReader<T> reader) throws IOException {
        int listSize = dis.readInt();
        List<T> list = new ArrayList<>();
        for (int j = 0; j < listSize; j++) {
            list.add(reader.read());
        }
        return list;
    }

    private void writeLink(DataOutputStream dos, Place place) throws IOException {
        dos.writeUTF(place.getHomePage().getName());
        dos.writeUTF(place.getHomePage().getUrl());

    }

    private void writePeriod(DataOutputStream dos, Place.Period period) throws IOException {
        dos.writeUTF(period.getStartDate().toString());
        dos.writeUTF(period.getEndDate().toString());
        dos.writeUTF(period.getPosition());
        dos.writeUTF(period.getDescription());
    }

    private interface ElementReader<T> {
        T read() throws IOException;
    }

    private interface ElementWriter<T> {
        void write(T t) throws IOException;
    }

    private <T> void writeCollection(DataOutputStream dos, Collection<T> collection, ElementWriter<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T element : collection) {
            writer.write(element);
        }
    }

    private interface ElementProcessor {
        void process() throws IOException;
    }

    private void readItems(DataInputStream dis, ElementProcessor processor) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            processor.process();
        }
    }
}

