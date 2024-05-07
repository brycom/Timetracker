package com.TimetrackerBackend.TimetrackerBackend.contollers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TimetrackerBackend.TimetrackerBackend.models.LoginResponsDTO;
import com.TimetrackerBackend.TimetrackerBackend.models.Statistics;

import com.TimetrackerBackend.TimetrackerBackend.models.User;
import com.TimetrackerBackend.TimetrackerBackend.services.AuthenticatinService;
import com.TimetrackerBackend.TimetrackerBackend.services.TaskService;
import com.TimetrackerBackend.TimetrackerBackend.services.UserService;

@RestController()
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;
    private final TaskService taskService;
    private final AuthenticatinService authenticationService;

    public UserController(UserService userService, TaskService taskService,
            AuthenticatinService authenticationService) {
        this.userService = userService;
        this.taskService = taskService;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/")
    public List<User> getUser() {
        return userService.getUsers();
    }

    @PostMapping("/user")

    public User createUser(@RequestBody User user) throws Exception {
        return authenticationService.createUser(user);
    }

    @GetMapping("/totaltime/{id}")
    public int getTotalTimeInMinutes(@PathVariable String id) {
        return userService.getTotalTimeInMinutes(id);
    }

    @GetMapping("/statistics/{id}")
    public Statistics getStatistics(@PathVariable String id) {
        return taskService.getStatistics(id);
    }

    @GetMapping("/statistics/{id}/{startDateStr}/{endDateStr}")
    public Statistics getStatistics(@PathVariable String id, @PathVariable String startDateStr,
            @PathVariable String endDateStr) {

        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);
        return taskService.getStatisticsForDates(id, startDate, endDate);

    }

    @PostMapping("/login")
    public LoginResponsDTO login(@RequestBody User user) {
        System.out.println("Användarnamn: " + user.getUsername() + " Lösenord:" + user.getPassword());
        return authenticationService.login(user.getUsername(), user.getPassword());
    }

    /*     @PostMapping("/logout")
    @ResponseBody
    public User logout(@RequestBody User user) {
        return userService.login(user);
    } */

}
