package com.sample.model.entity;

import java.util.Calendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Employee")
@Configurable
public class EmployeeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EmployeeId")
	private Integer employeeId;

	@Column(name = "LastName", length = 20)
	@NotNull
	private String lastName;

	@Column(name = "FirstName", length = 20)
	@NotNull
	private String firstName;

	@Column(name = "Title", length = 30)
	private String title;

	@Column(name = "BirthDate")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "MM")
	private Calendar birthDate;

	@Column(name = "HireDate")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "MM")
	private Calendar hireDate;

	@Column(name = "Address", length = 70)
	private String address;

	@Column(name = "City", length = 40)
	private String city;

	@Column(name = "State", length = 40)
	private String state;

	@Column(name = "Country", length = 40)
	private String country;

	@Column(name = "PostalCode", length = 10)
	private String postalCode;

	@Column(name = "Phone", length = 24)
	private String phone;

	@Column(name = "Fax", length = 24)
	private String fax;

	@Column(name = "Email", length = 60)
	private String email;

	@OneToMany(mappedBy = "supportRepId")
	private Set<CustomerEntity> customers;

	@OneToMany(mappedBy = "reportsTo")
	private Set<EmployeeEntity> employees;

	@ManyToOne
	@JoinColumn(name = "ReportsTo", referencedColumnName = "EmployeeId", insertable = false, updatable = false)
	private EmployeeEntity reportsTo;

	public Integer getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(Integer id) {
		this.employeeId = id;
	}

	public Set<CustomerEntity> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<CustomerEntity> customers) {
		this.customers = customers;
	}

	public Set<EmployeeEntity> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<EmployeeEntity> employees) {
		this.employees = employees;
	}

	public EmployeeEntity getReportsTo() {
		return reportsTo;
	}

	public void setReportsTo(EmployeeEntity reportsTo) {
		this.reportsTo = reportsTo;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Calendar getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Calendar birthDate) {
		this.birthDate = birthDate;
	}

	public Calendar getHireDate() {
		return hireDate;
	}

	public void setHireDate(Calendar hireDate) {
		this.hireDate = hireDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
