package org.example.DAO;

import org.example.models.User;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<User> getAll() {

        return entityManager.createQuery("select u from User u ", User.class).getResultList();
    }

    @Override
    public void add(User user) {
        if (user.getId() == null) {
            entityManager.persist(user);
        } else {
            entityManager.merge(user);
        }
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(entityManager.find(User.class, id));

    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    /*@Override
    public void update(Long id, User UpdateUser) {
        User userToBeUpdate = entityManager.find(User.class, id);
        userToBeUpdate.setName(UpdateUser.getName());
        userToBeUpdate.setAge(UpdateUser.getAge());
        userToBeUpdate.setEmail(UpdateUser.getEmail());

    }*/

    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }
}
