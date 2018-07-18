package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlaceSection extends Section {

    private final Map<String, Place> places = new HashMap<>();

    public void addPlace(String name, String url, LocalDate startDate, LocalDate endDate, String position, String description) {
        if (places.containsKey(name)) {
            places.get(name).addPeriod(startDate, endDate, position, description);
        } else {
            places.put(name, new Place(name, url, startDate, endDate, position, description));
        }
    }

    public List<Place> getPlaces() {
        return new ArrayList<>(places.values());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        places.forEach((name, place) -> builder.append("\n").append(place));
        return builder.toString();
    }
}
