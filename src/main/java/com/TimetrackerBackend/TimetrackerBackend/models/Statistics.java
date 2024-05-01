package com.TimetrackerBackend.TimetrackerBackend.models;

import java.util.List;

public class Statistics {

    private String User;
    private int totaltime;
    private List<Category> timePerCategory;
    private List<Task> tasks;

    public Statistics(String user, int totaltime, List<Category> timePerCategory, List<Task> tasks) {
        User = user;
        this.totaltime = totaltime;
        this.timePerCategory = timePerCategory;
        this.tasks = tasks;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public int getTotaltime() {
        return totaltime;
    }

    public void setTotaltime(int totaltime) {
        this.totaltime = totaltime;
    }

    public List<Category> getTimePerCategory() {
        return timePerCategory;
    }

    public void setTimePerCategory(List<Category> timePerCategory) {
        this.timePerCategory = timePerCategory;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

}
