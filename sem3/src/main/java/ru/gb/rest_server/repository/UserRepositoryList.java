package ru.gb.rest_server.repository;

import org.springframework.stereotype.Repository;
import ru.gb.rest_server.domain.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryList implements UserRep {
    List<User> userList = new ArrayList<>();

    @Override
    public User addUser(User newUser){
        if (newUser != null)
            userList.add(newUser);
        return newUser;
    }

    @Override
    public List<User> getUsers() {
        return userList;
    }
}
