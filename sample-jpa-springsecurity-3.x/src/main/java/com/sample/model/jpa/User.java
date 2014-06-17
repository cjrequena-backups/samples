package com.sample.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the USER database table.
 * 
 */
@Entity
@Table(name="USER")
@NamedQueries({
	@NamedQuery(name="USER.findAll", query="SELECT u FROM User u")
})
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_user", unique=true, nullable=false)
	private int idUser;

	@Column(nullable=false)
	private Boolean accountNonExpired;

	@Column(nullable=false)
	private Boolean accountNonLocked;

	@Column(length=50)
	private String alias;

	@Temporal(TemporalType.DATE)
	private Date birthday;

	@Column(nullable=false)
	private Boolean credentialsNonExpired;

	@Column(name="date_created", nullable=false)
	private Timestamp dateCreated;

	@Column(name="date_updated")
	private Timestamp dateUpdated;

	@Column(nullable=false, length=100)
	private String email;

	@Column(nullable=false)
	private Boolean enabled;

	@Column(name="facebook_account", length=100)
	private String facebookAccount;

	@Column(name="first_name", nullable=false, length=50)
	private String firstName;

	@Column(length=1)
	private String gender;

	@Column(name="google_account", length=100)
	private String googleAccount;

	@Column(name="id_city")
	private int idCity;

	@Column(name="id_country")
	private int idCountry;

	@Column(name="last_name", nullable=false, length=50)
	private String lastName;

	@Column(name="meta_data_1", length=45)
	private String metaData1;

	@Column(name="meta_data_2", length=45)
	private String metaData2;

	@Column(name="meta_data_3", length=45)
	private String metaData3;

	@Column(name="meta_data_4", length=45)
	private String metaData4;

	@Column(name="meta_data_5", length=45)
	private String metaData5;

	@Column(name="mobile_phone", length=20)
	private String mobilePhone;

	@Column(name="msn_account", length=100)
	private String msnAccount;

	@Column(name="other_phone", length=20)
	private String otherPhone;

	@Column(nullable=false, length=50)
	private String password;

	@Column(length=100)
	private String skype;

	@Column(name="zip_code", length=10)
	private String zipCode;

	//bi-directional many-to-many association to Role
	@ManyToMany
	@JoinTable(
		name="USER_ROLE"
		, joinColumns={
			@JoinColumn(name="id_user", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_role", nullable=false)
			}
		)
	private List<Role> roles;

	public User() {
	}

	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public Boolean getAccountNonExpired() {
		return this.accountNonExpired;
	}

	public void setAccountNonExpired(Boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public Boolean getAccountNonLocked() {
		return this.accountNonLocked;
	}

	public void setAccountNonLocked(Boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Boolean getCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public Timestamp getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Timestamp getDateUpdated() {
		return this.dateUpdated;
	}

	public void setDateUpdated(Timestamp dateUpdated) {
		this.dateUpdated = dateUpdated;
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

	public String getFacebookAccount() {
		return this.facebookAccount;
	}

	public void setFacebookAccount(String facebookAccount) {
		this.facebookAccount = facebookAccount;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGoogleAccount() {
		return this.googleAccount;
	}

	public void setGoogleAccount(String googleAccount) {
		this.googleAccount = googleAccount;
	}

	public int getIdCity() {
		return this.idCity;
	}

	public void setIdCity(int idCity) {
		this.idCity = idCity;
	}

	public int getIdCountry() {
		return this.idCountry;
	}

	public void setIdCountry(int idCountry) {
		this.idCountry = idCountry;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMetaData1() {
		return this.metaData1;
	}

	public void setMetaData1(String metaData1) {
		this.metaData1 = metaData1;
	}

	public String getMetaData2() {
		return this.metaData2;
	}

	public void setMetaData2(String metaData2) {
		this.metaData2 = metaData2;
	}

	public String getMetaData3() {
		return this.metaData3;
	}

	public void setMetaData3(String metaData3) {
		this.metaData3 = metaData3;
	}

	public String getMetaData4() {
		return this.metaData4;
	}

	public void setMetaData4(String metaData4) {
		this.metaData4 = metaData4;
	}

	public String getMetaData5() {
		return this.metaData5;
	}

	public void setMetaData5(String metaData5) {
		this.metaData5 = metaData5;
	}

	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getMsnAccount() {
		return this.msnAccount;
	}

	public void setMsnAccount(String msnAccount) {
		this.msnAccount = msnAccount;
	}

	public String getOtherPhone() {
		return this.otherPhone;
	}

	public void setOtherPhone(String otherPhone) {
		this.otherPhone = otherPhone;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSkype() {
		return this.skype;
	}

	public void setSkype(String skype) {
		this.skype = skype;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}