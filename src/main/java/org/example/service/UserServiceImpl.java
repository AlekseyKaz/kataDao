package org.example.service;

import org.example.DAO.UserDao;
import org.example.DAO.UserDaoImpl;
import org.example.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    private  UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    @Transactional
    public void add(User user) {
    userDao.add(user);
    }

    @Override
    @Transactional

    public void delete(int id) {
    userDao.delete(id);
    }

    @Override
    @Transactional
    public void update(int id, User user) {
    userDao.update(id, user);
    }

    @Override
    @Transactional
    public User findById(int id) {
        return userDao.findById(id);
    }
}
