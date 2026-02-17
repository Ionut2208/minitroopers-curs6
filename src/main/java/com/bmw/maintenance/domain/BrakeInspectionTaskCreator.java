package com.bmw.maintenance.domain;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.Map;

@ApplicationScoped
public class BrakeInspectionTaskCreator implements TaskCreator {

    @Override
    public TaskType getType() {
        return TaskType.BRAKE_INSPECTION;
    }

    @Override
    public MaintenanceTask create(String vin, String notes, Map<String, Object> additionalData) {
        return MaintenanceTask.createBrakeInspection(vin, notes);
    }
}
