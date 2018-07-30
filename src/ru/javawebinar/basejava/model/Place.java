package ru.javawebinar.basejava.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static ru.javawebinar.basejava.util.DateUtil.NOW;
import static ru.javawebinar.basejava.util.DateUtil.of;

public class Place implements Serializable {


    private final Link homePage;
    private List<Period> periodList = new ArrayList<>();


    public Place(String name, String url, Period... periods) {
        this(new Link(name, url), Arrays.asList(periods));
    }

    public Place(Link homePage, List<Period> periods) {
        this.homePage = homePage;
        this.periodList = periods;
    }

    public void addPeriod(LocalDate startDate, LocalDate endDate, String position, String description) {
        periodList.add(new Period(startDate, endDate, position, description));
    }

    @Override
    public String toString() {
        return String.format("%s\n%s", homePage, periodList.toString());
    }


    public static class Period implements Serializable {
        private LocalDate startDate;
        private LocalDate endDate;
        private String position;
        private String description;

        public Period(int startYear, Month startMonth, String position, String description) {
            this(of(startYear, startMonth), NOW, position, description);
        }

        public Period(int startYear, Month startMonth, int endYear, Month endMonth, String position, String description) {
            this(of(startYear, startMonth), of(endYear, endMonth), position, description);
        }

        public Period(LocalDate startDate, LocalDate endDate, String position, String description) {
            Objects.requireNonNull(startDate, "startDate must not be null");
            Objects.requireNonNull(endDate, "endDate must not be null");
            Objects.requireNonNull(position, "position must not be null");
            this.startDate = startDate;
            this.endDate = endDate;
            this.position = position;
            this.description = description;
        }

        @Override
        public String toString() {
            return String.format("n%tm/%<tY - %tm/%<tY %s\n%s\n", startDate, endDate, position, description);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Period period = (Period) o;

            if (!startDate.equals(period.startDate)) return false;
            if (!endDate.equals(period.endDate)) return false;
            if (!position.equals(period.position)) return false;
            return description != null ? description.equals(period.description) : period.description == null;
        }

        @Override
        public int hashCode() {
            int result = startDate.hashCode();
            result = 31 * result + endDate.hashCode();
            result = 31 * result + position.hashCode();
            result = 31 * result + (description != null ? description.hashCode() : 0);
            return result;
        }
    }
}
