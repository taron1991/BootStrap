package ru.kata.spring.boot_security.demo.dao;



import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserDao {
    void saveUser(User user);
    User findUserByName(String name);
    User findUser(Long id);
    List<User> listUsers();
    void deleteUser(Long id);
    void updateUser(User user);
}
