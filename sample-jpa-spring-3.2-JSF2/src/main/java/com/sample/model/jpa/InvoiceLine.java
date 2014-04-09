package com.sample.model.jpa;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Table(name = "InvoiceLine")
@Configurable
public class InvoiceLine {

	@PersistenceContext
    transient EntityManager entityManager;

	public static final EntityManager entityManager() {
        EntityManager em = new InvoiceLine().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countInvoiceLines() {
        return entityManager().createQuery("SELECT COUNT(o) FROM InvoiceLine o", Long.class).getSingleResult();
    }

	public static List<InvoiceLine> findAllInvoiceLines() {
        return entityManager().createQuery("SELECT o FROM InvoiceLine o", InvoiceLine.class).getResultList();
    }

	public static InvoiceLine findInvoiceLine(Integer invoiceLineId) {
        if (invoiceLineId == null) return null;
        return entityManager().find(InvoiceLine.class, invoiceLineId);
    }

	public static List<InvoiceLine> findInvoiceLineEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM InvoiceLine o", InvoiceLine.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            InvoiceLine attached = InvoiceLine.findInvoiceLine(this.invoiceLineId);
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
    public InvoiceLine merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        InvoiceLine merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "InvoiceLineId")
    private Integer invoiceLineId;

	public Integer getInvoiceLineId() {
        return this.invoiceLineId;
    }

	public void setInvoiceLineId(Integer id) {
        this.invoiceLineId = id;
    }

	@ManyToOne
    @JoinColumn(name = "TrackId", referencedColumnName = "TrackId", nullable = false)
    private Track trackId;

	@ManyToOne
    @JoinColumn(name = "InvoiceId", referencedColumnName = "InvoiceId", nullable = false)
    private Invoice invoiceId;

	@Column(name = "UnitPrice", precision = 10, scale = 2)
    @NotNull
    private BigDecimal unitPrice;

	@Column(name = "Quantity")
    @NotNull
    private Integer quantity;

	public Track getTrackId() {
        return trackId;
    }

	public void setTrackId(Track trackId) {
        this.trackId = trackId;
    }

	public Invoice getInvoiceId() {
        return invoiceId;
    }

	public void setInvoiceId(Invoice invoiceId) {
        this.invoiceId = invoiceId;
    }

	public BigDecimal getUnitPrice() {
        return unitPrice;
    }

	public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

	public Integer getQuantity() {
        return quantity;
    }

	public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
