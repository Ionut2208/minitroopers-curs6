package com.bmw.maintenance.persistence;

import com.bmw.maintenance.domain.MaintenanceTask;
import com.bmw.maintenance.domain.TaskStatus;
import com.bmw.maintenance.domaininteraction.MaintenanceTasks;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class MaintenanceTaskH2Repository implements MaintenanceTasks
{
    private final MaintenanceTaskPanacheRepository repository;
    private final MaintenanceTaskMapper mapper;

    @Inject
    public MaintenanceTaskH2Repository(MaintenanceTaskPanacheRepository repository,
                                       MaintenanceTaskMapper mapper)
    {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public MaintenanceTask create(MaintenanceTask task)
    {
        MaintenanceTaskEntity entity = mapper.toEntity(task);
        repository.persist(entity);
        return mapper.toDomain(entity);
    }

    @Override
    public MaintenanceTask findById(String taskId)
    {
        Long id = Long.parseLong(taskId);
        MaintenanceTaskEntity entity = repository.findById(id);

        if(entity == null)
            throw new NotFoundException("Task not found: " + taskId);

        return mapper.toDomain(entity);
    }

    @Override
    @Transactional
    public MaintenanceTask updateStatus(String taskId, TaskStatus newStatus)
    {
        Long id = Long.parseLong(taskId);
        MaintenanceTaskEntity entity = repository.findById(id);

        if(entity == null)
            throw new NotFoundException("Task not found: " + taskId);

        entity.setStatus(newStatus);
        entity.setUpdatedAt(LocalDateTime.now());

        return mapper.toDomain(entity);
    }

    @Override
    @Transactional
    public MaintenanceTask upsertNotes(String taskId, String notes)
    {
        Long id = Long.parseLong(taskId);
        MaintenanceTaskEntity entity = repository.findById(id);

        if(entity == null)
            throw new NotFoundException("Task not found: " + taskId);

        entity.setNotes(notes);
        entity.setUpdatedAt(LocalDateTime.now());

        return mapper.toDomain(entity);
    }

    @Override
    public List<MaintenanceTask> findAll()
    {
        return repository.listAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<MaintenanceTask> findByVin(String vin)
    {
        return repository.findByVin(vin)
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}

