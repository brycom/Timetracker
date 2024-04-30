package com.TimetrackerBackend.TimetrackerBackend.contollers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.TimetrackerBackend.TimetrackerBackend.models.Task;
import com.TimetrackerBackend.TimetrackerBackend.models.User;
import com.TimetrackerBackend.TimetrackerBackend.services.TaskService;
import com.TimetrackerBackend.TimetrackerBackend.services.UserService;

@Controller
@CrossOrigin("*")
public class UserController {

    private final UserService userService;
    private final TaskService taskService;

    public UserController(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping("/")
    @ResponseBody
    public List<User> getUser() {
        return userService.getUsers();
    }

    @PostMapping("/user")
    @ResponseBody
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/totaltime")
    @ResponseBody
    public int getTotalTimeInMinutes() {
        return userService.getTotalTimeInMinutes();
    }

    @GetMapping("/statistics")
    @ResponseBody
    public List<Task> getStatistics() {
        return taskService.getStatistics();
    }

    @PostMapping("/login")
    @ResponseBody
    public User login(@RequestBody User user) {
        return userService.login(user);
    }

    @PostMapping("/logout")
    @ResponseBody
    public User logout(@RequestBody User user) {
        return userService.login(user);
    }

}
