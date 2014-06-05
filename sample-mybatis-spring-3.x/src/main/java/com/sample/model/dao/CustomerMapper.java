package com.sample.model.dao;

import com.sample.model.pojos.Customer;
import com.sample.model.pojos.CustomerExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface CustomerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Customer
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    int countByExample(CustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Customer
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    int deleteByExample(CustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Customer
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    @Delete({
        "delete from Customer",
        "where CustomerId = #{customerid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer customerid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Customer
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    @Insert({
        "insert into Customer (CustomerId, FirstName, ",
        "LastName, Company, ",
        "Address, City, State, ",
        "Country, PostalCode, ",
        "Phone, Fax, Email, ",
        "SupportRepId)",
        "values (#{customerid,jdbcType=INTEGER}, #{firstname,jdbcType=VARCHAR}, ",
        "#{lastname,jdbcType=VARCHAR}, #{company,jdbcType=VARCHAR}, ",
        "#{address,jdbcType=VARCHAR}, #{city,jdbcType=VARCHAR}, #{state,jdbcType=VARCHAR}, ",
        "#{country,jdbcType=VARCHAR}, #{postalcode,jdbcType=VARCHAR}, ",
        "#{phone,jdbcType=VARCHAR}, #{fax,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR}, ",
        "#{supportrepid,jdbcType=INTEGER})"
    })
    int insert(Customer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Customer
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    int insertSelective(Customer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Customer
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    List<Customer> selectByExample(CustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Customer
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    @Select({
        "select",
        "CustomerId, FirstName, LastName, Company, Address, City, State, Country, PostalCode, ",
        "Phone, Fax, Email, SupportRepId",
        "from Customer",
        "where CustomerId = #{customerid,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    Customer selectByPrimaryKey(Integer customerid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Customer
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    int updateByExampleSelective(@Param("record") Customer record, @Param("example") CustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Customer
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    int updateByExample(@Param("record") Customer record, @Param("example") CustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Customer
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    int updateByPrimaryKeySelective(Customer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Customer
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    @Update({
        "update Customer",
        "set FirstName = #{firstname,jdbcType=VARCHAR},",
          "LastName = #{lastname,jdbcType=VARCHAR},",
          "Company = #{company,jdbcType=VARCHAR},",
          "Address = #{address,jdbcType=VARCHAR},",
          "City = #{city,jdbcType=VARCHAR},",
          "State = #{state,jdbcType=VARCHAR},",
          "Country = #{country,jdbcType=VARCHAR},",
          "PostalCode = #{postalcode,jdbcType=VARCHAR},",
          "Phone = #{phone,jdbcType=VARCHAR},",
          "Fax = #{fax,jdbcType=VARCHAR},",
          "Email = #{email,jdbcType=VARCHAR},",
          "SupportRepId = #{supportrepid,jdbcType=INTEGER}",
        "where CustomerId = #{customerid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Customer record);
}