package ru.kata.spring.boot_security.demo.util;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserGenerator {
    private static final String ROLE_ADMIN = "ADMIN";
    private static final String ROLE_USER = "USER";

    public static List<User> createUsersWithRoles() {
        User user1 = new User("user1", "user1", "user1FirstName", "user1SecondNAME", "user1@gmail.com", new HashSet<>(Set.of(new Role(ROLE_USER))));
        User user2 = new User("user2", "user2", "user2FirstName", "user2SecondNAME", "user2@gmail.com", new HashSet<>(Set.of(new Role(ROLE_USER))));
        User admin = new User("admin", "admin", "adminFirstName", "adminSecondNAME", "admin@gmail.com", new HashSet<>(Set.of(new Role(ROLE_ADMIN))));

        User adminuser = new User("adminuser", "adminuser", "adminuserFirstName", "adminuserSecondNAME", "adminuser@gmail.com", new HashSet<>(Set.of(new Role(ROLE_ADMIN), new Role(ROLE_USER))));
        return List.of(user1, user2, admin, adminuser);
    }
}
