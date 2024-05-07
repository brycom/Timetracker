package com.TimetrackerBackend.TimetrackerBackend.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Document(collection = "Users")
public class User implements UserDetails {
    @Id
    private String id;
    private String name;
    private String email;
    private String username;
    private String password;
    private Set<Role> role;
    private List<Task> tasks;
    private List<Task> defaultTasks;
    private int totalTimeInMinutes;

    public User() {
        super();
        this.role = new HashSet<Role>();
    }

    public User(String id, String name, String email, String username, String password, Set<Role> role,
            List<Task> tasks, List<Task> defaultTasks, int totalTimeInMinutes) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.tasks = tasks;
        this.defaultTasks = defaultTasks;
        this.totalTimeInMinutes = totalTimeInMinutes;
    }

    public User(String id, String name, String email, String username, String password, Set<Role> role) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;

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

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
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

    public void addDefaultTask(Task task) {
        defaultTasks.add(task);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.role;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
