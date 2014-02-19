package be.service;

import java.util.List;

import be.domain.Address;
import be.domain.User;

public interface UserService {
    User findById(Long id);

    List<User> findAll();

    void saveMe(User user);

    void delete(User user);

    void delete(Address address);

    Address findAddress(Long id);
}
