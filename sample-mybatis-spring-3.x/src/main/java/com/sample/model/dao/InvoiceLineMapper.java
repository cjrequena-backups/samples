package com.sample.model.dao;

import com.sample.model.pojos.InvoiceLine;
import com.sample.model.pojos.InvoiceLineExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface InvoiceLineMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table InvoiceLine
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    int countByExample(InvoiceLineExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table InvoiceLine
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    int deleteByExample(InvoiceLineExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table InvoiceLine
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    @Delete({
        "delete from InvoiceLine",
        "where InvoiceLineId = #{invoicelineid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer invoicelineid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table InvoiceLine
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    @Insert({
        "insert into InvoiceLine (InvoiceLineId, InvoiceId, ",
        "TrackId, UnitPrice, ",
        "Quantity)",
        "values (#{invoicelineid,jdbcType=INTEGER}, #{invoiceid,jdbcType=INTEGER}, ",
        "#{trackid,jdbcType=INTEGER}, #{unitprice,jdbcType=DECIMAL}, ",
        "#{quantity,jdbcType=INTEGER})"
    })
    int insert(InvoiceLine record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table InvoiceLine
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    int insertSelective(InvoiceLine record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table InvoiceLine
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    List<InvoiceLine> selectByExample(InvoiceLineExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table InvoiceLine
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    @Select({
        "select",
        "InvoiceLineId, InvoiceId, TrackId, UnitPrice, Quantity",
        "from InvoiceLine",
        "where InvoiceLineId = #{invoicelineid,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    InvoiceLine selectByPrimaryKey(Integer invoicelineid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table InvoiceLine
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    int updateByExampleSelective(@Param("record") InvoiceLine record, @Param("example") InvoiceLineExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table InvoiceLine
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    int updateByExample(@Param("record") InvoiceLine record, @Param("example") InvoiceLineExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table InvoiceLine
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    int updateByPrimaryKeySelective(InvoiceLine record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table InvoiceLine
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    @Update({
        "update InvoiceLine",
        "set InvoiceId = #{invoiceid,jdbcType=INTEGER},",
          "TrackId = #{trackid,jdbcType=INTEGER},",
          "UnitPrice = #{unitprice,jdbcType=DECIMAL},",
          "Quantity = #{quantity,jdbcType=INTEGER}",
        "where InvoiceLineId = #{invoicelineid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(InvoiceLine record);
}