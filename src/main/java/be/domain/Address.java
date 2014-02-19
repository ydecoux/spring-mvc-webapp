package be.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(schema = "public")
public class Address {

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String street;
    @Column
    @Version
    private Long version;

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
     * The getter method of the street field.
     * 
     * @return the street value
     */
    public String getStreet() {
	return this.street;
    }

    /**
     * The setter method of the street field.
     * 
     * @param street
     *            the street value to set
     */
    public void setStreet(String street) {
	this.street = street;
    }

    /**
     * The getter method of the version field.
     * 
     * @return the version value
     */
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

}
