/*
 * $HeadURL$
 * $Revision$
 * $Date$
 * $Author$
 * 
 * Application: spring-mvc-webapp
 * Contractor: ARHS
 */
package be.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.domain.Address;
import be.domain.User;

/**
 * TODO.
 */
@Service
@Profile("hibernate")
public class UserServiceHibernateImpl implements UserService {
    @Autowired
    private SessionFactory sessionFactory;

    // Hibernate will set the flush mode to NEVER (or manual) when the readonly attribute is set to true
    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return (User) this.sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createCriteria(User.class).list();
    }

    @Override
    @Transactional(readOnly = false)
    public void saveMe(User user) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(User user) {
        this.sessionFactory.getCurrentSession().delete(user);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Address address) {
        this.sessionFactory.getCurrentSession().delete(address);
    }

    @Override
    public Address findAddress(Long id) {
        return (Address) this.sessionFactory.getCurrentSession().get(Address.class, id);
    }
}
