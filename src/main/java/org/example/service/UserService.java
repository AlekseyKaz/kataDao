package org.example.service;

import org.example.models.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    void add(User user);
    void delete(Long id);
    void update(Long id, User user);
    User findById(Long id);
}
