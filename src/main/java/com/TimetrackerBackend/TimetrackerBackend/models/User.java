package com.TimetrackerBackend.TimetrackerBackend.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private String username;
    private String password;
    private String role;
    private String token;
    private List<Task> tasks;
    private List<Task> defaultTasks;
    private int totalTimeInMinutes;

    public User() {
    }

    public User(String id, String name, String email, String username, String password, String role, String token,
            List<Task> tasks, List<Task> defaultTasks, int totalTimeInMinutes) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.token = token;
        this.tasks = tasks;
        this.defaultTasks = defaultTasks;
        this.totalTimeInMinutes = totalTimeInMinutes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public List<Task> getTasksForDates(LocalDate startDate, LocalDate endDate) {
        List<Task> tasksForDates = new ArrayList<Task>();
        for (Task task : tasks) {
            if ((task.getDate().isEqual(startDate) || task.getDate().isAfter(startDate))
                    && (task.getDate().isEqual(endDate) || task.getDate().isBefore(endDate))) {
                tasksForDates.add(task);
            }
        }

        return tasksForDates;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getDefaultTasks() {
        return defaultTasks;
    }

    public void setDefaultTasks(List<Task> defaultTasks) {
        this.defaultTasks = defaultTasks;
    }

    public int getTotalTimeInMinutes() {
        return totalTimeInMinutes;
    }

    public void setTotalTimeInMinutes(int totalTimeInMinutes) {
        this.totalTimeInMinutes = totalTimeInMinutes;
    }

}
