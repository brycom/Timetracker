package com.TimetrackerBackend.TimetrackerBackend.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        List<Task> tasks = user.getTasks();
        int timeSpent = 0;
        List<Category> timePerCategory = new ArrayList<Category>();

        for (Task task : tasks) {
            timeSpent += task.getTimeSpent();
            boolean categoryFound = false;

            for (Category category : timePerCategory) {
                if (task.getCategory().equals(category.getCategory())) {
                    category.setTimeSpent(category.getTimeSpent() + (int) task.getTimeSpent());
                    categoryFound = true;
                    break;
                }
            }

            if (!categoryFound) {
                timePerCategory.add(new Category(task.getCategory(), (int) task.getTimeSpent()));
            }
        }
        Statistics statistics = new Statistics(user.getName(), timeSpent, timePerCategory,
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
            boolean categoryFound = false;

            for (Category category : timePerCategory) {
                if (task.getCategory().equals(category.getCategory())) {
                    category.setTimeSpent(category.getTimeSpent() + (int) task.getTimeSpent());
                    categoryFound = true;
                    break;
                }
            }

            if (!categoryFound) {
                timePerCategory.add(new Category(task.getCategory(), (int) task.getTimeSpent()));
            }
        }
        Statistics statistics = new Statistics(user.getName(), timeSpent, timePerCategory,
                user.getTasks());

        return statistics;
    }

    public Task createTask(Task task) {
        return mongoOperations.insert(task, "Tasks");
    }

    public Task addTaskToUser(Task task, String userId) {
        User user = mongoOperations.findById(userId, User.class);
        System.out.println(user.getName());
        System.out.println(task.getHeadline());
        task.setId(UUID.randomUUID().toString());
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
        System.out.println(task.getHeadline());
        User user = mongoOperations.findById(userId, User.class);
        task.setId(UUID.randomUUID().toString());
        user.addDefaultTask(task);
        mongoOperations.save(user);
        return task;
    }

    public Task updateDefaultTask(Task task, String taskId) {
        if (mongoOperations.findById(taskId, Task.class) != null) {

            task.setId(taskId);
            return mongoOperations.save(task);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NO task with that id was found");
        }
    }

    public Task updateDefaultTaskForUser(Task task, String userId, String taskId) {
        if (mongoOperations.findById(userId, User.class) != null) {
            User user = mongoOperations.findById(userId, User.class);
            task.setId(taskId);
            List<Task> defaltTasks = user.getDefaultTasks();
            for (Task t : defaltTasks) {
                if (t.getId().equals(taskId)) {
                    defaltTasks.remove(t);
                    defaltTasks.add(task);
                    user.setDefaultTasks(defaltTasks);
                    break;
                }

            }
            mongoOperations.save(user);
            return task;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NO User with that id was found");
        }
    }

    public String deliteDefaultTask(String taskid) {
        Query query = Query.query(Criteria.where("id").is(taskid));
        mongoOperations.remove(query, Task.class);
        return "DELETED";
    }

    public String deliteDefaultTaskForUser(String userId, String taskId) {
        User user = mongoOperations.findById(userId, User.class);
        List<Task> defaltTasks = user.getDefaultTasks();
        for (Task t : defaltTasks) {
            if (t.getId().equals(taskId)) {
                defaltTasks.remove(t);
                user.setDefaultTasks(defaltTasks);
                break;
            }

        }
        mongoOperations.save(user);
        return "DELETED";
    }

    public List<Task> getTasksForUser(String userId) {
        User user = mongoOperations.findById(userId, User.class);
        return user.getTasks();
    }

}
