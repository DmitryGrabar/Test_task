package com.github.grabar.bus_task.entity;

import java.util.Objects;

public class Bus {

    private CompanyName companyName;
    private TimeTable timeTable;

    public Bus() {
    }

    public Bus(CompanyName companyName, TimeTable timeTable) {
        this.companyName = companyName;
        this.timeTable = timeTable;
    }

    public CompanyName getCompanyName() {
        return companyName;
    }

    public void setCompanyName(CompanyName companyName) {
        this.companyName = companyName;
    }

    public TimeTable getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(TimeTable timeTable) {
        this.timeTable = timeTable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bus)) return false;
        Bus bus = (Bus) o;
        return companyName == bus.companyName &&
                Objects.equals(timeTable, bus.timeTable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyName, timeTable);
    }

    @Override
    public String toString() {
        return "" + companyName + " " + timeTable.getDeparture() + " " + timeTable.getArrival();
    }
}
