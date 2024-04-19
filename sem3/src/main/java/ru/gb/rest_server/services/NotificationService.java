package ru.gb.rest_server.services;

import org.springframework.stereotype.Service;
import ru.gb.rest_server.domain.User;

@Service
public class NotificationService {

    public void notifyUser(User user) {
        System.out.println("=".repeat(100));
        System.out.println("A new user has been created: " + user);
        System.out.println("=".repeat(100));
    }

    public void sendNotification(String s) {
        System.out.println("=".repeat(100));
        System.out.println(s);
        System.out.println("=".repeat(100));
    }
}
