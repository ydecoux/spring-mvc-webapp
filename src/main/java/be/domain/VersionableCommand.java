package be.domain;

public interface VersionableCommand<T> {

    Long getVersion();

    void setVersion(Long version);

    Long getEntityId();

    void setEntityId(Long entityId);

    Class<T> getEntityClass();
}
