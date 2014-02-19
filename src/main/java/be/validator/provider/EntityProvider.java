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

/**
 * TODO.
 */
public interface EntityProvider {
    Object getEntity(Class clazz, Long id);

    Session getHibernateSession();
}
