package com.sample.model.jpa;

import java.math.BigDecimal;
import java.util.Calendar;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;

@Configurable
@Entity
@Table(name = "Invoice")
public class Invoice {

	@OneToMany(mappedBy = "invoiceId")
    private Set<InvoiceLine> invoiceLines;

	@ManyToOne
    @JoinColumn(name = "CustomerId", referencedColumnName = "CustomerId", nullable = false)
    private Customer customerId;

	@Column(name = "InvoiceDate")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Calendar invoiceDate;

	@Column(name = "BillingAddress", length = 70)
    private String billingAddress;

	@Column(name = "BillingCity", length = 40)
    private String billingCity;

	@Column(name = "BillingState", length = 40)
    private String billingState;

	@Column(name = "BillingCountry", length = 40)
    private String billingCountry;

	@Column(name = "BillingPostalCode", length = 10)
    private String billingPostalCode;

	@Column(name = "Total", precision = 10, scale = 2)
    @NotNull
    private BigDecimal total;

	public Set<InvoiceLine> getInvoiceLines() {
        return invoiceLines;
    }

	public void setInvoiceLines(Set<InvoiceLine> invoiceLines) {
        this.invoiceLines = invoiceLines;
    }

	public Customer getCustomerId() {
        return customerId;
    }

	public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

	public Calendar getInvoiceDate() {
        return invoiceDate;
    }

	public void setInvoiceDate(Calendar invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

	public String getBillingAddress() {
        return billingAddress;
    }

	public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

	public String getBillingCity() {
        return billingCity;
    }

	public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

	public String getBillingState() {
        return billingState;
    }

	public void setBillingState(String billingState) {
        this.billingState = billingState;
    }

	public String getBillingCountry() {
        return billingCountry;
    }

	public void setBillingCountry(String billingCountry) {
        this.billingCountry = billingCountry;
    }

	public String getBillingPostalCode() {
        return billingPostalCode;
    }

	public void setBillingPostalCode(String billingPostalCode) {
        this.billingPostalCode = billingPostalCode;
    }

	public BigDecimal getTotal() {
        return total;
    }

	public void setTotal(BigDecimal total) {
        this.total = total;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "InvoiceId")
    private Integer invoiceId;

	public Integer getInvoiceId() {
        return this.invoiceId;
    }

	public void setInvoiceId(Integer id) {
        this.invoiceId = id;
    }

	@PersistenceContext
    transient EntityManager entityManager;

	public static final EntityManager entityManager() {
        EntityManager em = new Invoice().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countInvoices() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Invoice o", Long.class).getSingleResult();
    }

	public static List<Invoice> findAllInvoices() {
        return entityManager().createQuery("SELECT o FROM Invoice o", Invoice.class).getResultList();
    }

	public static Invoice findInvoice(Integer invoiceId) {
        if (invoiceId == null) return null;
        return entityManager().find(Invoice.class, invoiceId);
    }

	public static List<Invoice> findInvoiceEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Invoice o", Invoice.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Invoice attached = Invoice.findInvoice(this.invoiceId);
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
    public Invoice merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Invoice merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
}
