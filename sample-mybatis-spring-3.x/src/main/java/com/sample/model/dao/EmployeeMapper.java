package com.sample.model.dao;

import com.sample.model.pojos.Employee;
import com.sample.model.pojos.EmployeeExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface EmployeeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Employee
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    int countByExample(EmployeeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Employee
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    int deleteByExample(EmployeeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Employee
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    @Delete({
        "delete from Employee",
        "where EmployeeId = #{employeeid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer employeeid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Employee
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    @Insert({
        "insert into Employee (EmployeeId, LastName, ",
        "FirstName, Title, ",
        "ReportsTo, BirthDate, ",
        "HireDate, Address, ",
        "City, State, Country, ",
        "PostalCode, Phone, ",
        "Fax, Email)",
        "values (#{employeeid,jdbcType=INTEGER}, #{lastname,jdbcType=VARCHAR}, ",
        "#{firstname,jdbcType=VARCHAR}, #{title,jdbcType=VARCHAR}, ",
        "#{reportsto,jdbcType=INTEGER}, #{birthdate,jdbcType=TIMESTAMP}, ",
        "#{hiredate,jdbcType=TIMESTAMP}, #{address,jdbcType=VARCHAR}, ",
        "#{city,jdbcType=VARCHAR}, #{state,jdbcType=VARCHAR}, #{country,jdbcType=VARCHAR}, ",
        "#{postalcode,jdbcType=VARCHAR}, #{phone,jdbcType=VARCHAR}, ",
        "#{fax,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR})"
    })
    int insert(Employee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Employee
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    int insertSelective(Employee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Employee
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    List<Employee> selectByExample(EmployeeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Employee
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    @Select({
        "select",
        "EmployeeId, LastName, FirstName, Title, ReportsTo, BirthDate, HireDate, Address, ",
        "City, State, Country, PostalCode, Phone, Fax, Email",
        "from Employee",
        "where EmployeeId = #{employeeid,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    Employee selectByPrimaryKey(Integer employeeid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Employee
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Employee
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Employee
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    int updateByPrimaryKeySelective(Employee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Employee
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    @Update({
        "update Employee",
        "set LastName = #{lastname,jdbcType=VARCHAR},",
          "FirstName = #{firstname,jdbcType=VARCHAR},",
          "Title = #{title,jdbcType=VARCHAR},",
          "ReportsTo = #{reportsto,jdbcType=INTEGER},",
          "BirthDate = #{birthdate,jdbcType=TIMESTAMP},",
          "HireDate = #{hiredate,jdbcType=TIMESTAMP},",
          "Address = #{address,jdbcType=VARCHAR},",
          "City = #{city,jdbcType=VARCHAR},",
          "State = #{state,jdbcType=VARCHAR},",
          "Country = #{country,jdbcType=VARCHAR},",
          "PostalCode = #{postalcode,jdbcType=VARCHAR},",
          "Phone = #{phone,jdbcType=VARCHAR},",
          "Fax = #{fax,jdbcType=VARCHAR},",
          "Email = #{email,jdbcType=VARCHAR}",
        "where EmployeeId = #{employeeid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Employee record);
}