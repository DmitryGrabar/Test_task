package com.github.grabar.bus_task.entity;

import java.time.LocalTime;
import java.util.Objects;

public class TimeTable {

    private LocalTime departure;
    private LocalTime arrival;

    public TimeTable() {
    }

    public TimeTable(LocalTime departure, LocalTime arrival) {
        this.departure = departure;
        this.arrival = arrival;
    }

    public TimeTable(String departure, String arrival) {
        this.departure = LocalTime.parse(departure);
        this.arrival = LocalTime.parse(arrival);
    }

    public LocalTime getDeparture() {
        return departure;
    }

    public void setDeparture(LocalTime departure) {
        this.departure = departure;
    }

    public LocalTime getArrival() {
        return arrival;
    }

    public void setArrival(LocalTime arrival) {
        this.arrival = arrival;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimeTable)) return false;
        TimeTable timeTable = (TimeTable) o;
        return departure.equals(timeTable.departure) &&
                arrival.equals(timeTable.arrival);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departure, arrival);
    }

    @Override
    public String toString() {
        return "TimeTable{" +
                "departure=" + departure +
                ", arrival=" + arrival +
                '}';
    }
}
