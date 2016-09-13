package com.msiupik.tracker.repository;

import com.msiupik.tracker.model.Task;

import java.util.Collection;
import java.util.List;

/**
 * Created by msiupik on 13/09/16.
 */
public interface TaskRepository {
    public Task findByName(String name);
    public void update(Task task);
    public void update(Collection<Task> tasks);
    public void create(Task task);
    public Collection<Task> findAll();
    public Collection<Task> findAllActive();
    public void removeByName(String name);
}
