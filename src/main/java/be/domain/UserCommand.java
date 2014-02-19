package be.domain;

public class UserCommand implements VersionableCommand<User> {

    private User user;
    private Long id;
    private Long version;

    public Long getVersion() {

	return this.version;
    }

    public Long getEntityId() {
	return this.id;
    }

    public Class<User> getEntityClass() {
	return User.class;
    }

    /**
     * The getter method of the user field.
     * 
     * @return the user value
     */
    public User getUser() {
	return this.user;
    }

    /**
     * The setter method of the user field.
     * 
     * @param user
     *            the user value to set
     */
    public void setUser(User user) {
	this.user = user;
    }

    public void setVersion(Long version) {
	this.version = version;

    }

    public void setEntityId(Long entityId) {
	this.id = entityId;
    }

}
