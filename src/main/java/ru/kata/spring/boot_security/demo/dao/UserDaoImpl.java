package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public User findEmail(String email) {
        Query query = em.createQuery("SELECT u FROM User u WHERE u.email = : e");
        query.setParameter("e", email);
        return (User) query.getSingleResult();
    }

    @Override
    public List<User> getUsers() {
        return em.createQuery("SELECT u FROM User u").getResultList();
    }

    @Override
    public void save(User user) {
        em.persist(user);
    }

    @Override
    public User findUser(int id) {
        return em.find(User.class, id);
    }

    @Override
    public void update(User user) {
        em.merge(user);
    }

    @Override
    public void delete(int id) {
        User user = em.find(User.class, id);
        em.remove(user);
    }
}