package com.github.grabar.bus_task.service.impl;

import com.github.grabar.bus_task.entity.Bus;
import com.github.grabar.bus_task.entity.CompanyName;
import com.github.grabar.bus_task.entity.TimeTable;
import com.github.grabar.bus_task.service.BusService;

import java.time.LocalTime;
import java.util.*;

import static java.time.temporal.ChronoUnit.MINUTES;

public class BusServiceImpl implements BusService {
    private static final long EFFECTIVE_TIME = 60; //minutes

    @Override
    public List<Bus> getEffectiveBuses(List<Bus> buses) {
        LinkedList<Bus> tempList = new LinkedList<>(buses);
        ListIterator<Bus> iterator = tempList.listIterator();
        while (iterator.hasNext()) {
            Bus bus = iterator.next();
            long timeDiff = getTimeDiff(bus.getTimeTable());
            if (timeDiff <= 0 || timeDiff >= EFFECTIVE_TIME) {
                iterator.remove();
                continue;
            }
            removeLessEffectiveBus(tempList, iterator, bus);
        }
        return new ArrayList<>(tempList);
    }

    private void removeLessEffectiveBus(LinkedList<Bus> tempList, ListIterator<Bus> iterator, Bus bus) {
        LocalTime depTime = bus.getTimeTable().getDeparture();
        LocalTime arrTime = bus.getTimeTable().getArrival();
        for (Bus secondBus : tempList) {
            if (bus.equals(secondBus)) {
                continue;
            }
            if (bus.getCompanyName().equals(secondBus.getCompanyName())){
                boolean isDepartureTimeBefore = !depTime.isAfter(secondBus.getTimeTable().getDeparture());
                boolean isArrivalTimeAfter = !arrTime.isBefore(secondBus.getTimeTable().getArrival());
                if (isDepartureTimeBefore && isArrivalTimeAfter) {
                    iterator.remove();
                    break;
                }
            } else if (bus.getCompanyName().equals(CompanyName.GROTTY) && bus.getTimeTable().equals(secondBus.getTimeTable())){
                iterator.remove();
                break;
            }
        }
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
