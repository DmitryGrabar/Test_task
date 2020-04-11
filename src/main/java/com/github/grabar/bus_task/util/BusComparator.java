package com.github.grabar.bus_task.util;

import com.github.grabar.bus_task.entity.Bus;
import com.github.grabar.bus_task.entity.CompanyName;

import java.util.Comparator;

public class BusComparator implements Comparator<Bus> {

    @Override
    public int compare(Bus o1, Bus o2) {
        CompanyName name1 = o1.getCompanyName();
        CompanyName name2 = o2.getCompanyName();

        if (name1.equals(name2)) {
            if (o1.getTimeTable().getDeparture().isBefore(o2.getTimeTable().getDeparture())) {
                return -1;
            } else if (o1.getTimeTable().getDeparture().equals(o2.getTimeTable().getDeparture())) {
                return 0;
            } else {
                return 1;
            }
        } else if (name1.equals(CompanyName.POSH)){
            return -1;
        } else {
            return 1;
        }
    }
}
