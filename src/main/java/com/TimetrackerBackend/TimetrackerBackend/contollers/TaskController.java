package com.TimetrackerBackend.TimetrackerBackend.contollers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TimetrackerBackend.TimetrackerBackend.models.Task;
import com.TimetrackerBackend.TimetrackerBackend.services.TaskService;

@RestController()
@RequestMapping("/user")
@CrossOrigin("*")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/task")
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @PostMapping("/task/{userId}")
    public Task addTaskToUser(@RequestBody Task task, @PathVariable String userId) {
        return taskService.addTaskToUser(task, userId);
    }

    @GetMapping("task/{userId}")
    public List<Task> getTasksForUser(@PathVariable String userId) {
        System.out.println("Hamnar vi ens här?");
        return taskService.getTasksForUser(userId);
    }

    @GetMapping("/defaulttasks/{userId}")
    public List<Task> getDefaultTaskUser(@PathVariable String userId) {
        System.out.println("nu har du hamnat fel din jävel");
        return taskService.getDefaultTaskUser(userId);
    }

    @GetMapping("/defaulttasks")
    public List<Task> getDefaultTasks() {
        return taskService.getDefaultTasks();
    }

    @PostMapping("/defaulttasks/{userId}")
    public Task createDefaultTaskForUser(@RequestBody Task task, @PathVariable String userId) {
        System.out.println(task.getHeadline() + "det här borde hända");
        return taskService.createDefaultTaskForUser(task, userId);
    }

    @PatchMapping("/defaulttasks/{taskId}")
    public Task updateDefaultTask(@RequestBody Task task, @PathVariable String taskId) {
        return taskService.updateDefaultTask(task, taskId);
    }

    @PatchMapping("/defaulttasks/{userId}/{taskId}")
    public Task updateDefaultTaskForUser(@RequestBody Task task, @PathVariable String userId,
            @PathVariable String taskId) {
        return taskService.updateDefaultTaskForUser(task, userId, taskId);
    }

    @DeleteMapping("/defaulttasks/{taskId}")
    public String deliteDefaultTask(@PathVariable String taskId) {
        return taskService.deliteDefaultTask(taskId);
    }

    @DeleteMapping("/defaulttasks/{userId}/{taskId}")
    public String deliteDefaultTaskForUser(@PathVariable String userId, @PathVariable String taskId) {
        return taskService.deliteDefaultTaskForUser(userId, taskId);
    }

    @PatchMapping("/starttask/{userId}/{taskId}/{startTime}")
    public List<Task> startTask(@PathVariable String userId, @PathVariable String taskId,
            @PathVariable String startTime) {
        return taskService.startTask(userId, taskId, startTime);
    }

    @PatchMapping("/stoptask/{userId}/{taskId}/{endTime}")
    public List<Task> stopTask(@PathVariable String userId, @PathVariable String taskId,
            @PathVariable String endTime) {

        return taskService.stopTask(userId, taskId, endTime);
    }

}
