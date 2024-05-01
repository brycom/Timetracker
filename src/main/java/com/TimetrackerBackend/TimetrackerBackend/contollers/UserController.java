package com.TimetrackerBackend.TimetrackerBackend.contollers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.ResponseBody;

import com.TimetrackerBackend.TimetrackerBackend.models.Statistics;

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

    @GetMapping("/totaltime/{id}")
    @ResponseBody
    public int getTotalTimeInMinutes(@PathVariable String id) {
        return userService.getTotalTimeInMinutes(id);
    }

    @GetMapping("/statistics/{id}")
    @ResponseBody
    public Statistics getStatistics(@PathVariable String id) {
        return taskService.getStatistics(id);
    }

    @GetMapping("/statistics/{id}/{startDateStr}/{endDateStr}")
    @ResponseBody
    public Statistics getStatistics(@PathVariable String id, @PathVariable String startDateStr,
            @PathVariable String endDateStr) {

        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);
        return taskService.getStatisticsForDates(id, startDate, endDate);

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
