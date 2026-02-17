package com.bmw.maintenance.domain;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.Map;

@ApplicationScoped
public class TireServiceTaskCreator implements TaskCreator
{
    @Override
    public TaskType getType()
    {
        return TaskType.TIRE_SERVICE;
    }

    @Override
    public MaintenanceTask create(String vin, String notes, Map<String, Object> additionalData) {
        TirePosition pos = null;
        Object posObj = additionalData.get("tirePosition");
        if (posObj != null) {
            pos = TirePosition.valueOf(posObj.toString().toUpperCase());
        }

        if (pos == null) {
            throw new IllegalArgumentException("tirePosition is required for TIRE_SERVICE");
        }

        return MaintenanceTask.createTireService(vin, notes, pos);
    }
}
