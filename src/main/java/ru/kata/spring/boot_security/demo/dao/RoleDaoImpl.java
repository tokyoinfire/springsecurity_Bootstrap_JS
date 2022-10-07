package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role findRoles(String role) {
        Query query = entityManager.createQuery("SELECT r FROM Role r WHERE r.role = : role");
        query.setParameter("role", role);
        return (Role) query.getSingleResult();
    }

    @Override
    @Transactional
    public void save(Role role) {
        entityManager.persist(role);
    }
}