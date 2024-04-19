package ru.gb.rest_server.services;

import org.springframework.stereotype.Service;
import ru.gb.rest_server.domain.User;
import ru.gb.rest_server.repository.UserRepositoryDB;
import ru.gb.rest_server.repository.UserRepositoryList;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DataProcessingService {

    private final UserRepositoryDB repository;

    // включение зависимости через конструктор
    public DataProcessingService(UserRepositoryDB repository) {
        this.repository = repository;
    }

    public List<User> getUsersList(){
        return repository.getUsers();
    }

    public void addUserToList(User user) {
        repository.addUser(user);
    }

    public List<User> sortUsersByAge() {
        return repository.getUsers().stream().sorted(Comparator.comparing(User::getAge))
                .collect(Collectors.toList());
    }

    public List<User> filterUsersByAge(Integer age) {
        return repository.getUsers().stream()
                .filter(user -> Objects.equals(user.getAge(), age))
                .collect(Collectors.toList());
    }

    public double calculateAverageAge() {
        return repository.getUsers().stream()
                .mapToInt(User::getAge)
                .average()
                .orElse(0);
    }

}
