package com.github.grabar.bus_task.util;

import com.github.grabar.bus_task.entity.Bus;
import com.github.grabar.bus_task.entity.CompanyName;
import com.github.grabar.bus_task.entity.TimeTable;
import com.github.grabar.bus_task.service.impl.BusServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;

@RunWith(MockitoJUnitRunner.class)
public class BusServiceImplTest {

    @InjectMocks
    private BusServiceImpl busServiceImpl = new BusServiceImpl();

    @Test
    public void testGetEffectiveBuses() {
        Bus bus1 = new Bus(CompanyName.POSH, new TimeTable("17:25", "18:01"));
        Bus bus2 = new Bus(CompanyName.POSH, new TimeTable("10:15", "11:10"));
        Bus bus3 = new Bus(CompanyName.GROTTY, new TimeTable("12:45", "13:25"));
        Bus bus4 = new Bus(CompanyName.POSH, new TimeTable("10:10", "11:00"));
        Bus bus5 = new Bus(CompanyName.POSH, new TimeTable("12:05", "12:30"));
        Bus bus6 = new Bus(CompanyName.POSH, new TimeTable("12:05", "14:30"));
        Bus bus7 = new Bus(CompanyName.GROTTY, new TimeTable("12:05", "14:30"));

        List<Bus> buses = Arrays.asList(bus1, bus2, bus3, bus4, bus5, bus6, bus7);
        List<Bus> correctList = Arrays.asList(bus4,bus2, bus5, bus3, bus1);
        List<Bus> resultList = busServiceImpl.getEffectiveBuses(buses);

        resultList.sort(Comparator.comparing(bus -> bus.getTimeTable().getDeparture()));
        Assert.assertEquals(correctList,resultList);
    }

    @Test
    public void testGetEffectiveBusesFromCustomer() {
        Bus bus1 = new Bus(CompanyName.POSH, new TimeTable("10:12", "11:11"));
        Bus bus2 = new Bus(CompanyName.POSH, new TimeTable("12:12", "13:11"));
        Bus bus3 = new Bus(CompanyName.POSH, new TimeTable("10:00", "10:59"));
        Bus bus4 = new Bus(CompanyName.POSH, new TimeTable("10:01", "10:02"));

        List<Bus> buses = Arrays.asList(bus1, bus2, bus3, bus4);
        List<Bus> correctList = Arrays.asList(bus4, bus1, bus2);
        List<Bus> resultList = busServiceImpl.getEffectiveBuses(buses);

        resultList.sort(Comparator.comparing(bus -> bus.getTimeTable().getDeparture()));
        Assert.assertEquals(correctList, resultList);
    }
}
