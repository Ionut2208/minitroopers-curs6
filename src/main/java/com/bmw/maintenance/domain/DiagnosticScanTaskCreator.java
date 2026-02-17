package com.bmw.maintenance.domain;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

@ApplicationScoped
public class DiagnosticScanTaskCreator implements TaskCreator
{
    public TaskType getType()
    {
        return TaskType.DIAGNOSTIC_SCAN;
    }

    @Override
    public MaintenanceTask create(String vin, String notes, Map<String, Object> additionalData) {
        // errorCodes: make sure it’s a List<String>
        Object codesObj = additionalData.get("errorCodes");
        List<String> errorCodes = null;
        if (codesObj instanceof List<?> list) {
            errorCodes = list.stream().map(Object::toString).toList();
        }

        // scannerType: enum
        ScannerType scanner = null;
        Object scannerObj = additionalData.get("scannerType");
        if (scannerObj != null) {
            scanner = ScannerType.valueOf(scannerObj.toString().toUpperCase());
        }

        return MaintenanceTask.createDiagnosticScan(vin, notes, errorCodes, scanner);
    }

}
