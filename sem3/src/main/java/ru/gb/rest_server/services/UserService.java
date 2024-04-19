package ru.gb.rest_server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.rest_server.domain.User;

@Service
public class UserService {

    @Autowired
    private NotificationService notificationService;

    // или можно произвести введение зависимостей через конструктор класса вместо @Autowired - тут не критично
    //    public UserService(NotificationService notificationService) {
    //        this.notificationService = notificationService;
    //    }

    public User createUser(String name, int age, String email) {
        User user = new User(name, email, age);
        notificationService.notifyUser(user); // Отправляем уведомление о создании нового пользователя
        return user;
    }
}
