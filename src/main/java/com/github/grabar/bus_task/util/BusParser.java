package com.github.grabar.bus_task.util;

import com.github.grabar.bus_task.entity.Bus;
import com.github.grabar.bus_task.entity.CompanyName;
import com.github.grabar.bus_task.entity.TimeTable;
import org.apache.log4j.Logger;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class BusParser {
    private static final Logger LOG = Logger.getLogger(TimeTable.class);

    public List<Bus> parse(List<String> data) {
        List<Bus> buses = new ArrayList<>();
        for (String line : data) {
            String[] arr = line.split(" ");
            String companyName = arr[0];
            TimeTable timeTable;
            try {
                timeTable = new TimeTable(LocalTime.parse(arr[1]), LocalTime.parse(arr[2]));
                Bus bus = new Bus(CompanyName.valueOf(companyName.toUpperCase()), timeTable);
                buses.add(bus);
            } catch (DateTimeParseException e) {
                LOG.error("Error while parsing departure and arrival time. Please check time format in the input file.");
            }
        }
        return buses;
    }

}
