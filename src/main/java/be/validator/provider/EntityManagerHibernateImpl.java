/*
 * $HeadURL$
 * $Revision$
 * $Date$
 * $Author$
 * 
 * Application: spring-mvc-webapp
 * Contractor: ARHS
 */
package be.validator.provider;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * TODO.
 */
@Component
@Profile("hibernate")
public class EntityManagerHibernateImpl implements EntityProvider {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Object getEntity(Class clazz, Long id) {
        return this.sessionFactory.getCurrentSession().get(clazz, id);
    }

    @Override
    public Session getHibernateSession() {
        return this.sessionFactory.getCurrentSession();
    }
}
