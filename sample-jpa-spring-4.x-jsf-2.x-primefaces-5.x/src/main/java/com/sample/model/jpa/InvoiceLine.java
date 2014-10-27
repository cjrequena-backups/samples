package com.sample.model.jpa;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;

@Entity
@Table(name = "InvoiceLine")
@Configurable
public class InvoiceLine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "InvoiceLineId")
	private Integer invoiceLineId;

	@Column(name = "UnitPrice", precision = 10, scale = 2)
	@NotNull
	private BigDecimal unitPrice;

	@Column(name = "Quantity")
	@NotNull
	private Integer quantity;

	@ManyToOne
	@JoinColumn(name = "TrackId", referencedColumnName = "TrackId", nullable = false)
	private Track trackId;

	@ManyToOne
	@JoinColumn(name = "InvoiceId", referencedColumnName = "InvoiceId", nullable = false)
	private Invoice invoiceId;

	public Integer getInvoiceLineId() {
		return this.invoiceLineId;
	}

	public void setInvoiceLineId(Integer id) {
		this.invoiceLineId = id;
	}

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

	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
