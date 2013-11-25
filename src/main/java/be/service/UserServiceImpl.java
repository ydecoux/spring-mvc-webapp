package be.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.domain.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @PersistenceContext
    private EntityManager em;

    public UserServiceImpl() throws InterruptedException {
    }

    @PostConstruct
    public void afterPropertiesSet() {
    }

    public User findById(Long id) {
	return this.em.find(User.class, id);
    }

    public List<User> findAll() {
	return this.em.createQuery("from User").getResultList();
    }

    @Transactional
    public void save(User user) {
	user.setNickname(StringUtils.substring(user.getFirstname(), 0, 4));
	this.em.persist(user);
	this.em.flush();
    }

    public void delete(User user) {
	this.em.remove(user);
    }

}
