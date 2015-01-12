package com.sample.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class InvoicelineVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int invoiceLineId;

	private int invoiceId;

	private int quantity;

	private int trackId;

	private BigDecimal unitPrice;

	public InvoicelineVO() {
	}

	public int getInvoiceLineId() {
		return this.invoiceLineId;
	}

	public void setInvoiceLineId(int invoiceLineId) {
		this.invoiceLineId = invoiceLineId;
	}

	public int getInvoiceId() {
		return this.invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTrackId() {
		return this.trackId;
	}

	public void setTrackId(int trackId) {
		this.trackId = trackId;
	}

	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

}