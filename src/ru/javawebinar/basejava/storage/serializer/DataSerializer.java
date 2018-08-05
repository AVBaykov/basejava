package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
            dos.writeInt((int) sections
                    .values()
                    .stream().filter(Objects::nonNull)
                    .count());

            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                Section section = entry.getValue();
                if (section != null) {
                    dos.writeUTF(entry.getKey().name());
                    if (section instanceof TextSection) {
                        dos.writeUTF(((TextSection) section).getContent());
                    } else if (section instanceof ParagraphSection) {
                        List<String> list = ((ParagraphSection) section).getParagraphList();
                        dos.writeInt(list.size());
                        for (String s : list) {
                            dos.writeUTF(s);
                        }
                    } else if (section instanceof PlaceSection) {
                        List<Place> placeList = ((PlaceSection) section).getPlaces();
                        dos.writeInt(placeList.size());
                        for (Place place : placeList) {
                            String name = place.getHomePage().getName();
                            String url = place.getHomePage().getUrl();
                            dos.writeUTF(name);
                            if (url != null) {
                                dos.writeUTF(url);
                            } else {
                                dos.writeUTF("null");
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
                                    dos.writeUTF("null");
                                }
                            }
                        }
                    }
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
                if (type == SectionType.PERSONAL || type == SectionType.OBJECTIVE) {
                    resume.addSection(type, new TextSection(dis.readUTF()));
                } else if (type == SectionType.ACHIEVEMENT || type == SectionType.QUALIFICATIONS) {
                    List<String> list = new ArrayList<>();
                    int listSize = dis.readInt();
                    for (int j = 0; j < listSize; j++) {
                        list.add(dis.readUTF());
                    }
                    resume.addSection(type, new ParagraphSection(list));
                } else if (type == SectionType.EXPERIENCE || type == SectionType.EDUCATION) {
                    List<Place> places = new ArrayList<>();
                    int placeSize = dis.readInt();
                    for (int j = 0; j < placeSize; j++) {
                        Link link;
                        String name = dis.readUTF();
                        String url = dis.readUTF();
                        if (url.equals("null")) {
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
                            if (description.equals("null")) {
                                periodList.add(new Place.Period(start, end, position, null));
                            } else {
                                periodList.add(new Place.Period(start, end, position, description));
                            }
                        }
                        places.add(new Place(link, periodList));
                    }
                    resume.addSection(type, new PlaceSection(places));
                }
            }
            return resume;
        }
    }
}

