package com.github.grabar.bus_task.service.impl;

import com.github.grabar.bus_task.entity.Bus;
import com.github.grabar.bus_task.entity.CompanyName;
import com.github.grabar.bus_task.entity.TimeTable;
import com.github.grabar.bus_task.service.BusService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.time.temporal.ChronoUnit.MINUTES;

public class BusServiceImpl implements BusService {
    private static final long EFFECTIVE_TIME = 60; //minutes

    @Override
    public List<Bus> getEffectiveBuses(List<Bus> buses) {
        Map<Long, Bus> busesWithDriveTime = new HashMap<>();

        for (Bus bus : buses) {
            long timeDiff = getTimeDiff(bus.getTimeTable());
            if (timeDiff > 0 && timeDiff < EFFECTIVE_TIME) {
                if (!busesWithDriveTime.containsKey(timeDiff) || bus.getCompanyName().equals(CompanyName.POSH)) {
                    busesWithDriveTime.put(timeDiff, bus);
                }
            }
        }
        return new ArrayList<>(busesWithDriveTime.values());
    }

    @Override
    public int findFirstCompanyNameOccurrence(List<Bus> buses, CompanyName companyName) {
        int firstCompanyNameOccurrence = -1;
        for (int i = 0; i < buses.size(); i++) {
            if (buses.get(i).getCompanyName().equals(companyName)) {
                firstCompanyNameOccurrence = i;
                break;
            }
        }
        return firstCompanyNameOccurrence;
    }

    private long getTimeDiff(TimeTable timeTable) {
        return MINUTES.between(timeTable.getDeparture(), timeTable.getArrival());
    }
}
