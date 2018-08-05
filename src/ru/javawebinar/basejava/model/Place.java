package ru.javawebinar.basejava.model;

import ru.javawebinar.basejava.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static ru.javawebinar.basejava.util.DateUtil.NOW;
import static ru.javawebinar.basejava.util.DateUtil.of;

@XmlAccessorType(XmlAccessType.FIELD)
public class Place implements Serializable {
    private static final long serialVersionUID = 1L;


    private Link homePage;
    private List<Period> periodList = new ArrayList<>();

    public Place() {
    }

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

    public Link getHomePage() {
        return homePage;
    }

    public List<Period> getPeriodList() {
        return periodList;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return Objects.equals(homePage, place.homePage) &&
                Objects.equals(periodList, place.periodList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, periodList);
    }

    @Override
    public String toString() {
        return String.format("%s\n%s", homePage, periodList.toString());
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Period implements Serializable {
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate startDate;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate endDate;
        private String position;
        private String description;

        public Period() {
        }

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

        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public String getPosition() {
            return position;
        }

        public String getDescription() {
            return description;
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
            return Objects.equals(startDate, period.startDate) &&
                    Objects.equals(endDate, period.endDate) &&
                    Objects.equals(position, period.position) &&
                    Objects.equals(description, period.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(startDate, endDate, position, description);
        }
    }
}
