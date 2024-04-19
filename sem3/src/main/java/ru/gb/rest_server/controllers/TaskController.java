package ru.gb.rest_server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.gb.rest_server.domain.User;
import ru.gb.rest_server.services.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private DataProcessingService dataSrvc;

    @GetMapping
    public List<String> getAllTasks()
    {
        List<String> tasks = new ArrayList<>();
        tasks.add("sort");
        tasks.add("filter");
        tasks.add("calc");
        return  tasks;
    }

    @GetMapping("/sort")//localhost:8080/tasks/sort
    public List<User> sortUsersByAge(){
        return dataSrvc.sortUsersByAge();
    }

    @GetMapping("/filter/{age}") // выбираем всех, возраст которых равен age
    public List<User> filterUsersByAge(@PathVariable Integer age){
        return dataSrvc.filterUsersByAge(age);
    }

    @GetMapping("/calc")
    public double calculateAverageAge(){
        return dataSrvc.calculateAverageAge();
    }

}
