package com.TimetrackerBackend.TimetrackerBackend.models;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Tasks")
public class Task {
    @Id
    private String id;
    private String headline;
    private String description;
    private String category;
    private LocalDate date;
    private String startTime;
    private String endTime;

    public Task() {
    }

    public Task(String headline, String description, String category, LocalDate date, String startTime,
            String endTime) {
        this.id = UUID.randomUUID().toString();
        this.headline = headline;
        this.description = description;
        this.category = category;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Task(String id, String headline, String description, String category, LocalDate date, String startTime,
            String endTime) {
        this.id = id;
        this.headline = headline;
        this.description = description;
        this.category = category;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public long getTimeSpent() {
        LocalTime startTimeObj = LocalTime.parse(this.startTime);
        LocalTime endTimeObj = LocalTime.parse(this.endTime);

        Duration timeSpent = Duration.between(startTimeObj, endTimeObj);

        return timeSpent.toMinutes();
    }

}
