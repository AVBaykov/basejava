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

    public void addStartDate(String nameOfOrganisation, LocalDate startDate) {
        Place place = places.get(nameOfOrganisation);
        place.startDate = startDate;
    }

    public void addEndDate(String nameOfOrganisation, LocalDate endDate) {
        Place place = places.get(nameOfOrganisation);
        place.endDate = endDate;
    }

    public void addPosition(String nameOfOrganisation, String position) {
        Place place = places.get(nameOfOrganisation);
        place.position = position;
    }

    public void addDescription(String nameOfOrganisation, List<String> description) {
        Place place = places.get(nameOfOrganisation);
        place.description = description;
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
