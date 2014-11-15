package com.sample.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.sample.model.dao.IEmployeeDAO;
import com.sample.model.domain.Employee;

@Component("employeeJDBCTemplateDAO")
public class EmployeeJDBCTemplateDAO implements IEmployeeDAO {

	@Autowired
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Employee employee) {
		// TODO Auto-generated method stub

	}

	@Override
	public Employee getById(int id) {
		String query = "select EmployeeId, FirstName, LastName, Title, ReportsTo, BirthDate, HireDate, Address, City, State, Country, PostalCode, Phone, Fax, Email from Employee where EmployeeId = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		// using RowMapper anonymous class, we can create a separate RowMapper for reuse
		Employee employee = jdbcTemplate.queryForObject(query, new Object[] { id }, new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {

				Employee employee = new Employee();
				employee.setEmployeeId(rs.getInt("EmployeeId"));
				employee.setFirstName(rs.getString("FirstName"));
				employee.setLastName(rs.getString("LastName"));
				employee.setEmail(rs.getString("Email"));

				return employee;
			}
		});

		return employee;
	}

	@Override
	public void update(Employee employee) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Employee> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
