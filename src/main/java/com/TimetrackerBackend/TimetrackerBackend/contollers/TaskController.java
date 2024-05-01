package com.TimetrackerBackend.TimetrackerBackend.contollers;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.TimetrackerBackend.TimetrackerBackend.models.Task;
import com.TimetrackerBackend.TimetrackerBackend.services.TaskService;

@Controller
@CrossOrigin("*")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/task")
    @ResponseBody
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @PostMapping("/task/{userId}")
    @ResponseBody
    public Task addTaskToUser(@RequestBody Task task, @PathVariable String userId) {
        return taskService.addTaskToUser(task, userId);
    }

    /*     @GetMapping("/statistics/{id}")
    @ResponseBody
    public Statistics getStatistics(@PathVariable String id) {
        return taskService.getStatistics(id);
    }
     */
}
