package ru.gb.example3_sem3_hometask.services;

import ru.gb.example3_sem3_hometask.domain.User;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    private final DataProcessingService dataProcessingService;
    private final UserService userService;
    private final NotificationService notificationService;

    public RegistrationService(
            DataProcessingService dataProcessingService,
            UserService userService,
            NotificationService notificationService) {
        this.dataProcessingService = dataProcessingService;
        this.userService = userService;
        this.notificationService = notificationService;
    }

    public DataProcessingService getDataProcessingService() {
        return dataProcessingService;
    }

    public User progressRegistration(String name, int age, String email) {
        User user = userService.createUser(name, age, email);
        dataProcessingService.addUserToList(user);
        notificationService.notifyUser(user);
        return user;
    }

}
