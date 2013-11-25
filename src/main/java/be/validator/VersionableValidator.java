package be.validator;

import java.util.Comparator;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.EntityMode;
import org.hibernate.SessionFactory;
import org.hibernate.impl.SessionImpl;
import org.hibernate.persister.entity.EntityPersister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import be.domain.Versionable;
import be.exception.WrongVersionException;

@Component
public class VersionableValidator implements Validator {

    private static final Logger LOGGER = LoggerFactory
	    .getLogger(VersionableValidator.class);

    @PersistenceContext
    private EntityManager em;

    public boolean supports(Class<?> paramClass) {
	return Versionable.class.isAssignableFrom(paramClass);
    }

    public void validate(Object object, Errors errors) {
	if (object instanceof Versionable) {
	    Versionable versionable = (Versionable) object;
	    LOGGER.debug(
		    "Versionable entity passed to validation. Entity : {}.",
		    object.getClass());
	    // We use hibernate so we will get a Session instance
	    SessionImpl session = (SessionImpl) this.em.getDelegate();
	    SessionFactory sf = session.getSessionFactory();
	    Map info = sf.getAllClassMetadata();
	    Object metadata = info.get(object.getClass().getName());
	    if (metadata instanceof EntityPersister) {
		EntityPersister persister = (EntityPersister) metadata;
		EntityMode mode = session.getEntityMode();
		Object currentVersion = persister.getCurrentVersion(
			persister.getIdentifier(versionable, mode), session);
		Object version = persister.getVersion(versionable, mode);
		Comparator versionComparator = persister.getVersionComparator();
		if (((currentVersion != null) && (version != null) && (versionComparator != null))
			&& (versionComparator.compare(currentVersion, version) != 0)) {
		    throw new WrongVersionException("Stop doing shit please");
		}
	    }
	}

    }
}
