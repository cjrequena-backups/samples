package com.sample.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the USER_TEMPORARY database table.
 * 
 */
@Entity
@Table(name="USER_TEMPORARY")
@NamedQueries({
	@NamedQuery(name="USER_TEMPORARY.findAll", query="SELECT u FROM UserTemporary u")
})
public class UserTemporary implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_user_temporary", unique=true, nullable=false)
	private int idUserTemporary;

	@Column(nullable=false, length=100)
	private String email;

	@Column(nullable=false)
	private Boolean enabled;

	@Column(name="first_name", nullable=false, length=50)
	private String firstName;

	@Column(name="last_name", nullable=false, length=50)
	private String lastName;

	@Column(name="mobile_phone", nullable=false, length=20)
	private String mobilePhone;

	@Column(nullable=false, length=32)
	private String password;

	@Column(name="uuid", nullable=false, length=36)
	private String uuid;

	public UserTemporary() {
	}

	public int getIdUserTemporary() {
		return this.idUserTemporary;
	}

	public void setIdUserTemporary(int idUserTemporary) {
		this.idUserTemporary = idUserTemporary;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}