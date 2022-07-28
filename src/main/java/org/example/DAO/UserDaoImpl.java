package org.example.DAO;

import org.example.models.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao{
    @PersistenceContext
    private final EntityManager entityManager;

    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<User> getAll() {

        return entityManager.createQuery("select u from User u ", User.class).getResultList();
    }

    @Override
    public void add(User user) {
    entityManager.persist(user);

    }

    @Override
    public void delete(int id) {
    entityManager.remove(entityManager.find(User.class,id));

    }

    @Override
    public void update(int id, User UpdateUser) {
    User userToBeUpdate = entityManager.find(User.class,id);
    userToBeUpdate.setName(UpdateUser.getName());
    userToBeUpdate.setAge(UpdateUser.getAge());
    userToBeUpdate.setEmail(UpdateUser.getEmail());

    }

    @Override
    public User findById(int id) {
        return entityManager.find(User.class, id);
    }
}
