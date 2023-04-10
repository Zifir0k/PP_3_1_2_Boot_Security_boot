package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final RoleService roleService;

    public UserServiceImpl(UserDao userDao, RoleService roleService) {
        this.userDao = userDao;
        this.roleService = roleService;
    }

    @Override
    public User findUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    @Override
    @Transactional
    public void saveOrUpdate(User user) {
        List<String> allRolesFromDataBaseString = roleService.getRoles()
                .stream()
                .map(Role::getRole)
                .toList();
        List<Role> newRoles = new ArrayList<>();
        for (Role role : user.getRoles()) {
            if (allRolesFromDataBaseString.contains(role.getRole())) {
                newRoles.add(roleService.findRoleByName(role.getRole()));
            }
        }
        user.setRoles(newRoles);

        String currentPassword = user.getPassword();
        String passwordFromDataBase = userDao.findUserById(user.getId()).getPassword();

        if (user.getId() == null) {
            user.setPassword(new BCryptPasswordEncoder().encode(currentPassword));
            userDao.saveUser(user);
        } else {
            if (!currentPassword.equals(passwordFromDataBase)) {
                user.setPassword(new BCryptPasswordEncoder().encode(currentPassword));
            }
            userDao.updateUser(user);
        }
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) {
        User userByLogin = userDao.findUserByEmail(email);
        return new org.springframework.security.core.userdetails.User(userByLogin.getEmail(), userByLogin.getPassword(), userByLogin.getAuthorities());
    }
}