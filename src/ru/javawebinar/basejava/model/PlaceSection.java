package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlaceSection extends Section {

    private List<Place> places = new ArrayList<>();

    public void addPlace(String nameOfOrganisation) {
        places.add(new Place(nameOfOrganisation));
    }

    public void addStartDate(int index, LocalDate startDate) {
        places.get(index).startDate = startDate;
    }

    public void addEndDate(int index, LocalDate endDate) {
        places.get(index).endDate = endDate;
    }

    public void addPosition(int index, String position) {
        places.get(index).position = position;
    }

    public void addDescription(int index, String description) {
        places.get(index).description = description;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        places.forEach(place -> builder.append("\n").append(place));
        return builder.toString();
    }

    private class Place {
        private String nameOfOrganisation;
        private LocalDate startDate;
        private LocalDate endDate;
        private String position = "";
        private String description;

        Place(String nameOfOrganisation) {
            this.nameOfOrganisation = nameOfOrganisation;
        }

        @Override
        public String toString() {
            return String.format("%s \n%tm/%<tY - %tm/%<tY %s\n%s\n", nameOfOrganisation, startDate, endDate, position, description);
        }
    }
}
