package com.github.grabar.bus_task.util;

import com.github.grabar.bus_task.entity.Bus;
import com.github.grabar.bus_task.entity.CompanyName;
import com.github.grabar.bus_task.entity.TimeTable;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BusComparatorTest {

    @InjectMocks
    private BusComparator comparator = new BusComparator();

    @Test
    public void testCompareTwoEqualPosh() {
        Bus bus1 = new Bus(CompanyName.POSH, new TimeTable("10:15", "10:15"));
        Bus bus2 = new Bus(CompanyName.POSH, new TimeTable("10:15", "10:15"));
        int result = comparator.compare(bus1, bus2);
        Assert.assertEquals(0,result);
    }

    @Test
    public void testCompareGrottyAndPoshWithEqualTime() {
        Bus bus1 = new Bus(CompanyName.POSH, new TimeTable("17:25", "18:01"));
        Bus bus2 = new Bus(CompanyName.GROTTY, new TimeTable("17:25", "18:01"));
        int result = comparator.compare(bus1, bus2);
        Assert.assertEquals(-1,result);
    }

    @Test
    public void testCompareGrottyBeforePosh() {
        Bus bus1 = new Bus(CompanyName.POSH, new TimeTable("17:25", "18:01"));
        Bus bus2 = new Bus(CompanyName.GROTTY, new TimeTable("17:30", "19:55"));
        int result = comparator.compare(bus1, bus2);
        Assert.assertEquals(-1,result);
    }

    @Test
    public void testCompareGrottyMoreEffectiveThenPosh() {
        Bus bus1 = new Bus(CompanyName.POSH, new TimeTable("17:25", "18:01"));
        Bus bus2 = new Bus(CompanyName.GROTTY, new TimeTable("17:25", "18:00"));
        int result = comparator.compare(bus1, bus2);
        Assert.assertEquals(-1,result);
    }

    @Test
    public void testComparePoshMoreEffectiveThenPosh() {
        Bus bus1 = new Bus(CompanyName.POSH, new TimeTable("17:24", "17:50"));
        Bus bus2 = new Bus(CompanyName.POSH, new TimeTable("17:25", "18:01"));
        int result = comparator.compare(bus1, bus2);
        Assert.assertEquals(-1,result);
    }
}
