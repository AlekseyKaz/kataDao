package org.example.DAO;

import org.example.models.User;

import java.util.List;

public interface UserDao {
    List<User> getAll();
    void add(User user);
    void delete(int id);
    void update(int id, User user);
    User findById(int id);
}
