package com.msiupik.tracker.repository;

import com.msiupik.tracker.model.Task;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * Created by msiupik on 13/09/16.
 */
@Repository
public class InMemoryTaskRepository implements TaskRepository{

    private HashMap<String, Task> storage = new HashMap<>();

    @Override
    public Task findByName(String name) {
        return storage.get(name);
    }

    @Override
    public void update(Task task) {
        storage.put(task.getName(), task);
    }

    @Override
    public void update(Collection<Task> tasks) {
        tasks.forEach(t -> update(t));
    }

    @Override
    public void create(Task task) {
        storage.put(task.getName(), task);
    }

    @Override
    public Collection<Task> findAll() {
        return storage.values();
    }

    @Override
    public Collection<Task> findAllActive() {
        return findAll().stream().filter(t -> t.isActive()).collect(Collectors.toList());
    }

    @Override
    public void removeByName(String name) {
        storage.remove(name);
    }
}
