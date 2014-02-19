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

import be.domain.VersionableCommand;
import be.exception.WrongVersionException;
import be.validator.provider.EntityProvider;

@Component
public class VersionableCommandValidator implements Validator {
    private static final Logger LOGGER = LoggerFactory.getLogger(VersionableCommandValidator.class);
    @Autowired
    private EntityProvider entityProvider;

    @Override
    public boolean supports(Class<?> paramClass) {
        return VersionableCommand.class.isAssignableFrom(paramClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        if (object instanceof VersionableCommand) {
            VersionableCommand versionable = (VersionableCommand) object;
            Class entityClass = versionable.getEntityClass();
            Object entity = this.entityProvider.getEntity(entityClass, versionable.getEntityId());
            LOGGER.debug("Versionable entity passed to validation. Entity : {}.", new Object[] {entity.getClass() });
            // We use hibernate so we will get a Session instance
            SessionImpl session = (SessionImpl) this.entityProvider.getHibernateSession();
            SessionFactory sf = session.getSessionFactory();
            Map info = sf.getAllClassMetadata();
            Object metadata = info.get(entity.getClass().getName());
            if (metadata instanceof EntityPersister) {
                EntityPersister persister = (EntityPersister) metadata;
                EntityMode mode = session.getEntityMode();
                // We just issued a db select so the db version is equals to the
                // entity version
                Object dbVersion = persister.getVersion(entity, mode);
                Object uiVersion = versionable.getVersion();
                Comparator versionComparator = persister.getVersionComparator();
                if (((dbVersion != null) && (uiVersion != null)) && (versionComparator != null)
                    && (versionComparator.compare(dbVersion, uiVersion) != 0)) {
                    throw new WrongVersionException("Stop doing shit");
                }
            }
        }
    }
}
