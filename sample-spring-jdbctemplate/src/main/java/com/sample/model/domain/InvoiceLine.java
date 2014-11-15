package com.sample.model.domain;

import java.math.BigDecimal;

public class InvoiceLine {

	private Integer invoiceLineId;

	private BigDecimal unitPrice;

	private Integer quantity;

	private Track trackId;

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

}
