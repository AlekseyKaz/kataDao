package org.example.DAO;

import org.example.models.User;
import org.hibernate.Hibernate;
import org.springframework.orm.hibernate5.HibernateOperations;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class UserDaoImpl implements UserDao{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> index() {
       return em.createQuery("select p from User p", User.class).getResultList();

    }

    @Override
    public void save(User user) {
        em.getTransaction().begin(); /// нужно ли каждый раз открывать и закрывать?
        em.merge(user);
        em.getTransaction().commit();

    }

    @Override
    public void delete(int id) {
        em.getTransaction().begin();

        User user = em.find(User.class, id);
        em.remove(user);
        em.getTransaction().commit();

    }

    @Override
    public void update(int id, User user) {

    }

    @Override
    public User show(int id) {
        em.getTransaction().begin();

        User user = em.find(User.class, id);
        em.detach(user);
        em.getTransaction().commit();
        return user;
    }
}
