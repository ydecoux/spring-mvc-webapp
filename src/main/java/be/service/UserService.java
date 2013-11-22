package be.service;

import java.util.List;

import be.domain.User;

public interface UserService {
    User findById(Long id);

    List<User> findAll();

    void save(User user);
}
