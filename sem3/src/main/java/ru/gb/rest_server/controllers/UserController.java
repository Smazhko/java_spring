package ru.gb.rest_server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.gb.rest_server.services.RegistrationService;
import ru.gb.rest_server.domain.User;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private RegistrationService regSrvc;

    @GetMapping
    public List<User> userList() {
        return regSrvc.getUsersList();
    }

    @PostMapping("/body")
    public String userAddFromBody(@RequestBody User user)
    {
        regSrvc.processRegistration(user);
        return "User added from body!";
    }

    @PostMapping("/param")
    public String userAddFromParameters(@RequestParam String name, String email, Integer age){
        if (!name.isEmpty() && !email.isEmpty() && age != null)
            regSrvc.processRegistration(name, email, age);
        return "User added from params";
    }
}
