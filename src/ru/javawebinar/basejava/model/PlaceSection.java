package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlaceSection extends Section {

    private final List<Place> places = new ArrayList<>();

    public void addPlace(String name, String url, LocalDate startDate, LocalDate endDate, String position, String description) {
        places.add(new Place(name, url, startDate, endDate, position, description));
    }

    public List<Place> getPlaces() {
        return places;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        places.forEach(place -> builder.append("\n").append(place));
        return builder.toString();
    }

    private class Place {
        private Link nameOfOrganisation;
        private LocalDate startDate;
        private LocalDate endDate;
        private String position;
        private String description;

        Place(String name, String url, LocalDate startDate, LocalDate endDate, String position, String description) {
            Objects.requireNonNull(startDate, "startDate must not be null");
            Objects.requireNonNull(endDate, "endDate must not be null");
            Objects.requireNonNull(position, "position must not be null");
            this.nameOfOrganisation = new Link(name, url);
            this.startDate = startDate;
            this.endDate = endDate;
            this.position = position;
            this.description = description;
        }

        @Override
        public String toString() {
            return String.format("%s \n%tm/%<tY - %tm/%<tY %s\n%s\n", nameOfOrganisation, startDate, endDate, position, description);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Place place = (Place) o;

            if (!nameOfOrganisation.equals(place.nameOfOrganisation)) return false;
            if (!startDate.equals(place.startDate)) return false;
            if (!endDate.equals(place.endDate)) return false;
            if (!position.equals(place.position)) return false;
            return description != null ? description.equals(place.description) : place.description == null;
        }

        @Override
        public int hashCode() {
            int result = nameOfOrganisation.hashCode();
            result = 31 * result + startDate.hashCode();
            result = 31 * result + endDate.hashCode();
            result = 31 * result + position.hashCode();
            result = 31 * result + (description != null ? description.hashCode() : 0);
            return result;
        }
    }
}
