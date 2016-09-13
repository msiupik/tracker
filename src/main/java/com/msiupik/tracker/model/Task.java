package com.msiupik.tracker.model;

import org.joda.time.DateTime;

import java.util.Comparator;
import java.util.Date;

/**
 * Created by msiupik on 06/09/16.
 */
public class Task {
    private String name;
    private long elapsedTime;
    private long lastUpdated;
    private long lastActivated;
    private TaskState state;

    public Task(){
        this.state = TaskState.INACTIVE;
        this.elapsedTime = 0l;
        this.lastUpdated = DateTime.now().getMillis();
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getElapsedTimeAsString(){
        long sec = elapsedTime / 1000;
        long min = sec / 60;
        long hours = min / 60;

        return String.format("%d h %d min %d s", hours, min % 60, sec % 60);
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public TaskState getTaskState(){
        return state;
    }

    public void activate(){
        if(state == TaskState.INACTIVE) {
            state = TaskState.ACTIVE;
            lastActivated = DateTime.now().getMillis();
            lastUpdated = lastActivated;
        }
    }

    public void deactivate(){
        if(state == TaskState.ACTIVE) {
            state = TaskState.INACTIVE;
            lastUpdated = DateTime.now().getMillis();
            elapsedTime += lastUpdated - lastActivated;
        }
    }

    public boolean isActive() {
        return TaskState.ACTIVE == this.state;
    }

    public static Comparator<Task> getByActivationTimeComparator(){
        return new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                int activationDiff = (int)(o2.lastActivated - o1.lastActivated);
                int updateDiff = (int)(o2.lastUpdated - o1.lastUpdated);

                if(activationDiff == 0)
                    return updateDiff;

                return activationDiff;
            }
        };
    }
}
