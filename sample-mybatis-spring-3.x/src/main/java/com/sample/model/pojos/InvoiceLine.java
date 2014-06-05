package com.sample.model.pojos;

import java.math.BigDecimal;

public class InvoiceLine {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column InvoiceLine.InvoiceLineId
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    private Integer invoicelineid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column InvoiceLine.InvoiceId
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    private Integer invoiceid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column InvoiceLine.TrackId
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    private Integer trackid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column InvoiceLine.UnitPrice
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    private BigDecimal unitprice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column InvoiceLine.Quantity
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    private Integer quantity;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column InvoiceLine.InvoiceLineId
     *
     * @return the value of InvoiceLine.InvoiceLineId
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    public Integer getInvoicelineid() {
        return invoicelineid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column InvoiceLine.InvoiceLineId
     *
     * @param invoicelineid the value for InvoiceLine.InvoiceLineId
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    public void setInvoicelineid(Integer invoicelineid) {
        this.invoicelineid = invoicelineid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column InvoiceLine.InvoiceId
     *
     * @return the value of InvoiceLine.InvoiceId
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    public Integer getInvoiceid() {
        return invoiceid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column InvoiceLine.InvoiceId
     *
     * @param invoiceid the value for InvoiceLine.InvoiceId
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    public void setInvoiceid(Integer invoiceid) {
        this.invoiceid = invoiceid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column InvoiceLine.TrackId
     *
     * @return the value of InvoiceLine.TrackId
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    public Integer getTrackid() {
        return trackid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column InvoiceLine.TrackId
     *
     * @param trackid the value for InvoiceLine.TrackId
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    public void setTrackid(Integer trackid) {
        this.trackid = trackid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column InvoiceLine.UnitPrice
     *
     * @return the value of InvoiceLine.UnitPrice
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    public BigDecimal getUnitprice() {
        return unitprice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column InvoiceLine.UnitPrice
     *
     * @param unitprice the value for InvoiceLine.UnitPrice
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    public void setUnitprice(BigDecimal unitprice) {
        this.unitprice = unitprice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column InvoiceLine.Quantity
     *
     * @return the value of InvoiceLine.Quantity
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column InvoiceLine.Quantity
     *
     * @param quantity the value for InvoiceLine.Quantity
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}