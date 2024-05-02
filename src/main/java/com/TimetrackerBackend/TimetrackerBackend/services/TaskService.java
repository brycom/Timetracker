package com.TimetrackerBackend.TimetrackerBackend.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.TimetrackerBackend.TimetrackerBackend.models.Category;
import com.TimetrackerBackend.TimetrackerBackend.models.Statistics;
import com.TimetrackerBackend.TimetrackerBackend.models.Task;
import com.TimetrackerBackend.TimetrackerBackend.models.User;

@Service
public class TaskService {

    private final MongoOperations mongoOperations;

    public TaskService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public Statistics getStatistics(String id) {
        User user = mongoOperations.findById(id, User.class);
        List<Category> timePerCategory = new ArrayList<Category>();
        for (Task task : user.getTasks()) {

            if (timePerCategory.size() <= 0) {
                timePerCategory.add(new Category(task.getCategory(), (int) task.getTimeSpent()));

            } else {
                for (Category category : timePerCategory) {
                    if (task.getCategory().equals(category.getCategory())) {

                        category.setTimeSpent(category.getTimeSpent() + (int) task.getTimeSpent());
                        System.out.println("i if");
                        break;
                    } else {
                        timePerCategory.add(new Category(task.getCategory(), (int) task.getTimeSpent()));
                        System.out.println("i else");
                        break;
                    }
                }
            }
        }
        Statistics statistics = new Statistics(user.getName(), user.getTotalTimeInMinutes(), timePerCategory,
                user.getTasks());

        return statistics;
    }

    public Statistics getStatisticsForDates(String id, LocalDate startDate, LocalDate endDate) {
        User user = mongoOperations.findById(id, User.class);
        List<Task> tasks = user.getTasksForDates(startDate, endDate);
        int timeSpent = 0;
        List<Category> timePerCategory = new ArrayList<Category>();
        for (Task task : tasks) {
            timeSpent += task.getTimeSpent();
            if (timePerCategory.size() <= 0) {
                timePerCategory.add(new Category(task.getCategory(), (int) task.getTimeSpent()));

            } else {
                for (Category category : timePerCategory) {
                    if (task.getCategory().equals(category.getCategory())) {

                        category.setTimeSpent(category.getTimeSpent() + (int) task.getTimeSpent());
                        break;
                    } else {
                        timePerCategory.add(new Category(task.getCategory(), (int) task.getTimeSpent()));
                        break;
                    }
                }
            }
        }
        Statistics statistics = new Statistics(user.getName(), timeSpent, timePerCategory,
                user.getTasks());

        return statistics;
    }

    public Task createTask(Task task) {
        return mongoOperations.save(task, "Tasks");
    }

    public Task addTaskToUser(Task task, String userId) {
        User user = mongoOperations.findById(userId, User.class);
        user.addTask(task);
        user.setTotalTimeInMinutes(user.getTotalTimeInMinutes() + (int) task.getTimeSpent());
        mongoOperations.save(user);
        return task;
    }

    public List<Task> getDefaultTaskUser(String userId) {
        return mongoOperations.findById(userId, User.class).getDefaultTasks();
    }

    public List<Task> getDefaultTasks() {

        return mongoOperations.find(new Query(), Task.class, "Tasks");
    }

    public Task createDefaultTaskForUser(Task task, String userId) {
        User user = mongoOperations.findById(userId, User.class);
        user.addDefaultTask(task);
        mongoOperations.save(user);
        return task;
    }

}
