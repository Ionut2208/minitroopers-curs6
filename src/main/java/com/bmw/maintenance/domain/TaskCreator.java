package com.bmw.maintenance.domain;

import java.util.Map;

public interface TaskCreator
{
    TaskType getType();
    MaintenanceTask create(String vin, String notes, Map<String, Object> additionalData);
}
