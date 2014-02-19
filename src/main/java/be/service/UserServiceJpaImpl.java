package be.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import be.domain.Address;
import be.domain.User;

@Service
@Profile("jpa")
public class UserServiceJpaImpl implements UserService {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public User findById(Long id) {
        return this.em.find(User.class, id);
    }

    @Override
    public List<User> findAll() {
        return this.em.createQuery("from User").getResultList();
    }

    @Override
    @Transactional(readOnly = false)
    public void saveMe(User user) {
        if (user.getId() == null) {
            this.em.persist(user);
        }
    }

    @Override
    public void delete(User user) {
        this.em.remove(user);
    }

    @Override
    public void delete(Address address) {
        this.em.remove(address);
    }

    @Override
    public Address findAddress(Long id) {
        return this.em.find(Address.class, id);
    }
}
