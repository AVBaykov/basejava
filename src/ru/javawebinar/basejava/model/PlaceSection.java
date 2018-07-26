package ru.javawebinar.basejava.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class PlaceSection extends Section {

    private final List<Place> places;

    public PlaceSection(Place... places) {
        this(Arrays.asList(places));
    }

    public PlaceSection(List<Place> places) {
        Objects.requireNonNull(places, "places must not be null");
        this.places = places;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlaceSection that = (PlaceSection) o;

        return places.equals(that.places);
    }

    @Override
    public int hashCode() {
        return places.hashCode();
    }
}
