package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataSerializer implements Serializer {
    @Override
    public void doWrite(OutputStream os, Resume resume) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            Map<SectionType, Section> sections = resume.getSections();
            dos.writeInt(sections.size());

            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                SectionType type = entry.getKey();
                dos.writeUTF(type.name());
                switch (type) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(((TextSection) entry.getValue()).getContent());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List<String> list = ((ParagraphSection) entry.getValue()).getParagraphList();
                        dos.writeInt(list.size());
                        for (String s : list) {
                            dos.writeUTF(s);
                        }
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Place> placeList = ((PlaceSection) entry.getValue()).getPlaces();
                        dos.writeInt(placeList.size());
                        for (Place place : placeList) {
                            String name = place.getHomePage().getName();
                            String url = place.getHomePage().getUrl();
                            dos.writeUTF(name);
                            if (url != null) {
                                dos.writeUTF(url);
                            } else {
                                dos.writeUTF("");
                            }
                            List<Place.Period> periodList = place.getPeriodList();
                            dos.writeInt(periodList.size());
                            for (Place.Period period : periodList) {
                                String start = period.getStartDate().toString();
                                String end = period.getEndDate().toString();
                                String position = period.getPosition();
                                String description = period.getDescription();
                                dos.writeUTF(start);
                                dos.writeUTF(end);
                                dos.writeUTF(position);
                                if (description != null) {
                                    dos.writeUTF(description);
                                } else {
                                    dos.writeUTF("");
                                }
                            }
                        }
                        break;
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            size = dis.readInt();
            for (int i = 0; i < size; i++) {
                SectionType type = SectionType.valueOf(dis.readUTF());
                switch (type) {
                    case PERSONAL:
                    case OBJECTIVE:
                        resume.addSection(type, new TextSection(dis.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List<String> list = new ArrayList<>();
                        int listSize = dis.readInt();
                        for (int j = 0; j < listSize; j++) {
                            list.add(dis.readUTF());
                        }
                        resume.addSection(type, new ParagraphSection(list));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Place> places = new ArrayList<>();
                        int placeSize = dis.readInt();
                        for (int j = 0; j < placeSize; j++) {
                            Link link;
                            String name = dis.readUTF();
                            String url = dis.readUTF();
                            if (url.isEmpty()) {
                                link = new Link(name, null);
                            } else {
                                link = new Link(name, url);
                            }
                            List<Place.Period> periodList = new ArrayList<>();
                            int periodSize = dis.readInt();
                            for (int k = 0; k < periodSize; k++) {
                                LocalDate start = LocalDate.parse(dis.readUTF());
                                LocalDate end = LocalDate.parse(dis.readUTF());
                                String position = dis.readUTF();
                                String description = dis.readUTF();
                                if (description.isEmpty()) {
                                    periodList.add(new Place.Period(start, end, position, null));
                                } else {
                                    periodList.add(new Place.Period(start, end, position, description));
                                }
                            }
                            places.add(new Place(link, periodList));
                        }
                        resume.addSection(type, new PlaceSection(places));
                        break;
                }
            }
            return resume;
        }
    }
}

