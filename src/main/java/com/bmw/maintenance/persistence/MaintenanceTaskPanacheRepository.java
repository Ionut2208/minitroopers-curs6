package com.bmw.maintenance.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class MaintenanceTaskPanacheRepository implements PanacheRepository<MaintenanceTaskEntity>
{
    public List<MaintenanceTaskEntity> findByVin(String vin)
    {
        return list("vin", vin);
    }
}
