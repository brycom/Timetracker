package com.TimetrackerBackend.TimetrackerBackend.contollers;

import java.util.List;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/defaulttasks/{userId}")
    @ResponseBody
    public List<Task> getDefaultTaskUser(@PathVariable String userId) {
        return taskService.getDefaultTaskUser(userId);
    }

    @GetMapping("/defaulttasks")
    @ResponseBody
    public List<Task> getDefaultTasks() {
        return taskService.getDefaultTasks();
    }

    @PostMapping("/defaulttasks/{userId}")
    @ResponseBody
    public Task createDefaultTaskForUser(@RequestBody Task task, @PathVariable String userId) {
        System.out.println(task.getHeadline() + "det här borde hända");
        return taskService.createDefaultTaskForUser(task, userId);
    }

}
