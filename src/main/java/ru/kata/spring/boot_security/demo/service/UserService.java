package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    User findUserByEmail(String email);

    List<User> getAllUsers();

    void deleteUser(Long id);

    void saveOrUpdate(User user);
}
