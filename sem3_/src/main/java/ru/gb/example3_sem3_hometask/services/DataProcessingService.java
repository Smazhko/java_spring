package ru.gb.example3_sem3_hometask.services;

import ru.gb.example3_sem3_hometask.domain.User;
import ru.gb.example3_sem3_hometask.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataProcessingService {
    private final UserRepository repository;

    public DataProcessingService(UserRepository repository) {
        this.repository = repository;
    }

    public UserRepository getRepository() {
        return repository;
    }

    public void addUserToList(User user) {
        repository.getUsers().add(user);
    }

    public List<User> sortUsersByAge(List<User> users) {
        return repository.getUsers().stream().sorted(Comparator.comparing(User::getAge))
                .collect(Collectors.toList());
    }

    public List<User> filterUsersByAge(List<User> users, int age) {
        return users.stream()
                .filter(user -> user.getAge() > age)
                .collect(Collectors.toList());
    }

    public double calculateAverageAge(List<User> users) {
        return users.stream()
                .mapToInt(User::getAge)
                .average()
                .orElse(0);
    }

}
