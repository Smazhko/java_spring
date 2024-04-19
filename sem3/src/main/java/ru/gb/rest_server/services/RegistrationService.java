package ru.gb.rest_server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.rest_server.domain.User;

import java.util.List;

@Service
public class RegistrationService {
    @Autowired
    private DataProcessingService dataProcessingSrvc;

    @Autowired
    private UserService userSrvc;

    @Autowired
    private NotificationService notifSrvc;

    private DataProcessingService getDataProcessingService() {
        return dataProcessingSrvc;
    }


    public void processRegistration(String name, String email, Integer age) {
        User newUser = userSrvc.createUser(name, age, email);
        dataProcessingSrvc.addUserToList(newUser);
        notifSrvc.sendNotification("Пользователь создан  и добавлен в базу: " + newUser);
    }

    public void processRegistration(User newUser) {
        dataProcessingSrvc.addUserToList(newUser);
        notifSrvc.sendNotification("Пользователь создан  и добавлен в базу: " + newUser);
    }

    public List<User> getUsersList() {
        return dataProcessingSrvc.getUsersList();
    }
}
