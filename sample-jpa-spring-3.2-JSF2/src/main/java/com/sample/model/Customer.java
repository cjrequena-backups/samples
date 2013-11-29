package com.sample.model;

import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

@Configurable
@Entity
@Table(name = "Customer")
public class Customer {

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	@PersistenceContext
    transient EntityManager entityManager;

	public static final EntityManager entityManager() {
        EntityManager em = new Customer().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countCustomers() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Customer o", Long.class).getSingleResult();
    }

	public static List<Customer> findAllCustomers() {
        return entityManager().createQuery("SELECT o FROM Customer o", Customer.class).getResultList();
    }

	public static Customer findCustomer(Integer customerId) {
        if (customerId == null) return null;
        return entityManager().find(Customer.class, customerId);
    }

	public static List<Customer> findCustomerEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Customer o", Customer.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	@Transactional
    public void persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }

	@Transactional
    public void remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Customer attached = Customer.findCustomer(this.customerId);
            this.entityManager.remove(attached);
        }
    }

	@Transactional
    public void flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }

	@Transactional
    public void clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }

	@Transactional
    public Customer merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Customer merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CustomerId")
    private Integer customerId;

	public Integer getCustomerId() {
        return this.customerId;
    }

	public void setCustomerId(Integer id) {
        this.customerId = id;
    }

	@OneToMany(mappedBy = "customerId")
    private Set<Invoice> invoices;

	@ManyToOne
    @JoinColumn(name = "SupportRepId", referencedColumnName = "EmployeeId")
    private Employee supportRepId;

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

	public Set<Invoice> getInvoices() {
        return invoices;
    }

	public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
    }

	public Employee getSupportRepId() {
        return supportRepId;
    }

	public void setSupportRepId(Employee supportRepId) {
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
}
