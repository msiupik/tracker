package com.msiupik.tracker.service;

import com.msiupik.tracker.model.Task;
import com.msiupik.tracker.model.TaskState;
import com.msiupik.tracker.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by msiupik on 06/09/16.
 */
@Service
public class DefaultTaskService implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Override
    public void create(Task task) {
        taskRepository.create(task);
    }

    @Override
    public void activate(String name) {
        Collection<Task> tasks = taskRepository.findAllActive();
        tasks.forEach(t -> t.deactivate());
        taskRepository.update(tasks);

        Task task = taskRepository.findByName(name);

        task.activate();
        taskRepository.update(task);
    }

    @Override
    public void deactivate(String name) {
        Task task = taskRepository.findByName(name);

        task.deactivate();
        taskRepository.update(task);
    }

    @Override
    public List<Task> list() {
        return taskRepository.findAll().stream()
                .sorted(Task.getByActivationTimeComparator())
                .collect(Collectors.toList());
    }

    @Override
    public void remove(String taskName) {
        taskRepository.removeByName(taskName);
    }
}
