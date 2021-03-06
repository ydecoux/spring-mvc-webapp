package be.validator;

import java.util.Comparator;
import java.util.Map;

import org.hibernate.EntityMode;
import org.hibernate.SessionFactory;
import org.hibernate.impl.SessionImpl;
import org.hibernate.persister.entity.EntityPersister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import be.domain.Versionable;
import be.exception.WrongVersionException;
import be.validator.provider.EntityProvider;

@Component
public class VersionableValidator implements Validator {
    private static final Logger LOGGER = LoggerFactory.getLogger(VersionableValidator.class);
    @Autowired
    private EntityProvider entityProvider;

    @Override
    public boolean supports(Class<?> paramClass) {
        return Versionable.class.isAssignableFrom(paramClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        if (true) {
            return;
        }
        if (object instanceof Versionable) {
            Versionable versionable = (Versionable) object;
            LOGGER.debug("Versionable entity passed to validation. Entity : {}.", object.getClass());
            // We use hibernate so we will get a Session instance
            SessionImpl session = (SessionImpl) this.entityProvider.getHibernateSession();
            SessionFactory sf = session.getSessionFactory();
            Map info = sf.getAllClassMetadata();
            Object metadata = info.get(object.getClass().getName());
            if (metadata instanceof EntityPersister) {
                EntityPersister persister = (EntityPersister) metadata;
                EntityMode mode = session.getEntityMode();
                Object currentVersion =
                    persister.getCurrentVersion(persister.getIdentifier(versionable, mode), session);
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
