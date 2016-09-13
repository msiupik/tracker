package com.msiupik.tracker.service;

import com.msiupik.tracker.model.Task;

import java.util.List;
import java.util.Stack;

/**
 * Created by msiupik on 06/09/16.
 */
public interface TaskService {

    /**
     * Creates the task
     * @param task
     */
    public void create(Task task);

    /**
     * Activates the task. Only one task can be active at any given time. The implementations should ensure that other tsaks are deactivated.
     * @param taskName
     */
    public void activate(String taskName);

    public void deactivate(String taskName);

    /**
     * Obtains the list of all tasks
     * @return
     */
    public List<Task> list();

    public void remove(String taskName);
}
