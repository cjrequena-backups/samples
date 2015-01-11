package com.sample.model.entity;

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
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
@Entity
@Table(name = "Customer")
public class CustomerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CustomerId")
	private Integer customerId;

	@Column(name = "FirstName", length = 40)
	@NotNull
	private String firstName;

	@Column(name = "LastName", length = 20)
	@NotNull
	private String lastName;

	@Column(name = "Company", length = 80)
	private String company;

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
	@NotNull
	private String email;

	@OneToMany(mappedBy = "customerId")
	private Set<InvoiceEntity> invoices;

	@ManyToOne
	@JoinColumn(name = "SupportRepId", referencedColumnName = "EmployeeId")
	private EmployeeEntity supportRepId;

	public Integer getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Integer id) {
		this.customerId = id;
	}

	public Set<InvoiceEntity> getInvoices() {
		return invoices;
	}

	public void setInvoices(Set<InvoiceEntity> invoices) {
		this.invoices = invoices;
	}

	public EmployeeEntity getSupportRepId() {
		return supportRepId;
	}

	public void setSupportRepId(EmployeeEntity supportRepId) {
		this.supportRepId = supportRepId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
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
