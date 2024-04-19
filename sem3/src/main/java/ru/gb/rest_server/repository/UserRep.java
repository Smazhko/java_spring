package ru.gb.rest_server.repository;

import ru.gb.rest_server.domain.User;

import java.util.List;

public interface UserRep {
    User addUser(User newUser);

    List<User> getUsers();
}
