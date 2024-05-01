package com.TimetrackerBackend.TimetrackerBackend.models;

public class Category {
    String category;
    int timeSpent;

    public Category(String category, int timeSpent) {
        this.category = category;
        this.timeSpent = timeSpent;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(int timeSpent) {
        this.timeSpent = timeSpent;
    }

}
