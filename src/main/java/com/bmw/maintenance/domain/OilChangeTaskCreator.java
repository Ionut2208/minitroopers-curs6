package com.bmw.maintenance.domain;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.Map;

@ApplicationScoped
public class OilChangeTaskCreator implements TaskCreator {

    @Override
    public TaskType getType()
    {
        return TaskType.OIL_CHANGE;
    }

    @Override
    public MaintenanceTask create(String vin, String notes, Map<String, Object> additionalData)
    {
        return MaintenanceTask.createOilChange(vin, notes);
    }
}
