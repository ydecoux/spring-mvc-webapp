package be.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(schema = "public")
public class User implements Versionable {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String firstname;
    @Column
    private String lastname;
    @Column
    private String nickname;
    @Column
    private Integer age;
    @Version
    @Column
    private Long version;

    /**
     * The default constructor for User.
     */
    public User() {
    }

    public User(String firstname, String lastname, String nickname) {
	this.firstname = firstname;
	this.lastname = lastname;
	this.nickname = nickname;
    }

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }

    /**
     * The getter method of the nickname field.
     * 
     * @return the nickname value
     */
    public String getNickname() {
	return this.nickname;
    }

    /**
     * The setter method of the nickname field.
     * 
     * @param nickname
     *            the nickname value to set
     */
    public void setNickname(String nickname) {
	this.nickname = nickname;
    }

    /**
     * The getter method of the id field.
     * 
     * @return the id value
     */
    public Long getId() {
	return this.id;
    }

    /**
     * The setter method of the id field.
     * 
     * @param id
     *            the id value to set
     */
    public void setId(Long id) {
	this.id = id;
    }

    /**
     * The getter method of the firstname field.
     * 
     * @return the firstname value
     */
    public String getFirstname() {
	return this.firstname;
    }

    /**
     * The setter method of the firstname field.
     * 
     * @param firstname
     *            the firstname value to set
     */
    public void setFirstname(String firstname) {
	this.firstname = firstname;
    }

    /**
     * The getter method of the lastname field.
     * 
     * @return the lastname value
     */
    public String getLastname() {
	return this.lastname;
    }

    /**
     * The setter method of the lastname field.
     * 
     * @param lastname
     *            the lastname value to set
     */
    public void setLastname(String lastname) {
	this.lastname = lastname;
    }

    public Long getVersion() {
	return this.version;
    }

    /**
     * The setter method of the version field.
     * 
     * @param version
     *            the version value to set
     */
    public void setVersion(Long version) {
	this.version = version;
    }

    /**
     * The setter method of the age field.
     * 
     * @param age
     *            the age value to set
     */
    public void setAge(Integer age) {
	this.age = age;
    }

    /**
     * The getter method of the age field.
     * 
     * @return the age value
     */
    public Integer getAge() {
	return this.age;
    }
}
