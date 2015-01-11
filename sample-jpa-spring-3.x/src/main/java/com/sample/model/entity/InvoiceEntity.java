package com.sample.model.entity;

import java.math.BigDecimal;
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

@Configurable
@Entity
@Table(name = "Invoice")
public class InvoiceEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "InvoiceId")
	private Integer invoiceId;

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

	@OneToMany(mappedBy = "invoiceId")
	private Set<InvoiceLineEntity> invoiceLines;

	@ManyToOne
	@JoinColumn(name = "CustomerId", referencedColumnName = "CustomerId", nullable = false)
	private CustomerEntity customerId;

	public Set<InvoiceLineEntity> getInvoiceLines() {
		return invoiceLines;
	}

	public void setInvoiceLines(Set<InvoiceLineEntity> invoiceLines) {
		this.invoiceLines = invoiceLines;
	}

	public CustomerEntity getCustomerId() {
		return customerId;
	}

	public void setCustomerId(CustomerEntity customerId) {
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

	public Integer getInvoiceId() {
		return this.invoiceId;
	}

	public void setInvoiceId(Integer id) {
		this.invoiceId = id;
	}

	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
