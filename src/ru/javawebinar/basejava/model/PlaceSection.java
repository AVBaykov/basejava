package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlaceSection extends Section {

    private Map<String, Place> places = new HashMap<>();

    PlaceSection(SectionType type) {
        super(type);
    }

    public void addPlace(String nameOfOrganisation) {
        places.put(nameOfOrganisation, new Place(nameOfOrganisation));
    }

    public void removePlace(String nameOfOrganisation) {
        places.remove(nameOfOrganisation);
    }

    private class Place {
        private String nameOfOrganisation;
        private LocalDate startDate;
        private LocalDate endDate;
        private String position;
        private List<String> description;

        Place(String nameOfOrganisation) {
            this.nameOfOrganisation = nameOfOrganisation;
        }
    }
}
