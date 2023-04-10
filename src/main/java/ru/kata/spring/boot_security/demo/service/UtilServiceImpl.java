package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UtilServiceImpl implements UtilService {

    private final UserService userService;

    private final RoleService roleService;

    public UtilServiceImpl(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public void generateStartRoles() {
        Role roleUser = new Role("USER");
        roleService.saveRole(roleUser);

        Role roleAdmin = new Role("ADMIN");
        roleService.saveRole(roleAdmin);
    }

    @Override
    @Transactional
    public void generateStartUsers() {
        User user1 = new User("user1@gmail.com", "user1", "user1", "user1", 31, new ArrayList<>(List.of(new Role("USER"))));
        userService.saveOrUpdate(user1);

        User user2 = new User("user2@gmail.com", "user2", "user2", "user2", 32, new ArrayList<>(List.of(new Role("USER"))));
        userService.saveOrUpdate(user2);

        User admin = new User("admin@gmail.com", "admin", "admin", "admin", 32, new ArrayList<>(List.of(new Role("ADMIN"))));
        userService.saveOrUpdate(admin);

        User adminuser = new User("adminuser@gmail.com", "adminuser", "adminuser", "adminuser", 32, new ArrayList<>(List.of(new Role("ADMIN"), new Role("USER"))));
        userService.saveOrUpdate(adminuser);
    }
}
