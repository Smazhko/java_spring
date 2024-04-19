package ru.gb.example3_sem3_hometask.services;

import ru.gb.example3_sem3_hometask.domain.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final NotificationService notificationService;

    public UserService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public User createUser(String name, int age, String email) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        user.setEmail(email);
        return user;
    }
}
