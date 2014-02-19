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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * TODO.
 */
@Component
@Profile("jpa")
public class EntityProviderJpaImpl implements EntityProvider {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Object getEntity(Class clazz, Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Session getHibernateSession() {
        return (Session) this.em.getDelegate();
    }
}
