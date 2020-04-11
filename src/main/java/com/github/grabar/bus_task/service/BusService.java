package com.github.grabar.bus_task.service;

import com.github.grabar.bus_task.entity.Bus;
import com.github.grabar.bus_task.entity.CompanyName;

import java.util.List;

public interface BusService {
    int findFirstCompanyNameOccurrence(List<Bus> buses, CompanyName companyName);

    List<Bus> getEffectiveBuses(List<Bus> buses);
}
