package com.TimetrackerBackend.TimetrackerBackend.contollers;

import java.util.List;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

    @GetMapping("task/{userId}")
    @ResponseBody
    public List<Task> getTasksForUser(@PathVariable String userId) {
        System.out.println("Hamnar vi ens här?");
        return taskService.getTasksForUser(userId);
    }

    @GetMapping("/defaulttasks/{userId}")
    @ResponseBody
    public List<Task> getDefaultTaskUser(@PathVariable String userId) {
        System.out.println("nu har du hamnat fel din jävel");
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

    @PatchMapping("/defaulttasks/{taskId}")
    @ResponseBody
    public Task updateDefaultTask(@RequestBody Task task, @PathVariable String taskId) {
        return taskService.updateDefaultTask(task, taskId);
    }

    @PatchMapping("/defaulttasks/{userId}/{taskId}")
    @ResponseBody
    public Task updateDefaultTaskForUser(@RequestBody Task task, @PathVariable String userId,
            @PathVariable String taskId) {
        return taskService.updateDefaultTaskForUser(task, userId, taskId);
    }

    @DeleteMapping("/defaulttasks/{taskId}")
    @ResponseBody
    public String deliteDefaultTask(@PathVariable String taskId) {
        return taskService.deliteDefaultTask(taskId);
    }

    @DeleteMapping("/defaulttasks/{userId}/{taskId}")
    @ResponseBody
    public String deliteDefaultTaskForUser(@PathVariable String userId, @PathVariable String taskId) {
        return taskService.deliteDefaultTaskForUser(userId, taskId);
    }

}
