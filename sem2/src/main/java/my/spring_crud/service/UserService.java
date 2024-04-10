package my.spring_crud.service;

import my.spring_crud.model.User;
import my.spring_crud.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return  userRepository.save(user);
    }

    public void deleteUserById(Integer id) {
        userRepository.deleteUserById(id);
    }

    public User updateUser(User newUser){
        return userRepository.updateUser(newUser);
    }
}