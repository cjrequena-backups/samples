package com.sample.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sample.architecture.commons.utils.ReflectionUtils;
import com.sample.model.dao.IEmployeeDAO;
import com.sample.model.domain.Employee;

@Component("employeeDAO")
public class EmployeeDAO implements IEmployeeDAO {

	@Autowired
	private DataSource dataSource;
	
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Employee employee) {
		String query = "insert into Employee (FirstName, LastName, Title) values (?,?,?)";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			// ps.setInt(1, employee.getEmployeeId());
			ps.setString(1, employee.getFirstName());
			ps.setString(2, employee.getLastName());
			ps.setString(3, employee.getTitle());
			// ps.setInt(4, employee.getReportsTo());
			// ps.setString(5, employee.getLastName());
			// ps.setString(6, employee.getLastName());
			// ps.setString(7, employee.getLastName());
			// ps.setString(8, employee.getLastName());
			// ps.setString(9, employee.getLastName());
			// ps.setString(10, employee.getLastName());
			// ps.setString(11, employee.getLastName());
			// ps.setString(12, employee.getLastName());
			// ps.setString(13, employee.getLastName());
			// ps.setString(14, employee.getLastName());

			int out = ps.executeUpdate();
			if (out != 0) {
				System.out.println("Employee saved with id=" + employee.getEmployeeId());
			} else {
				// System.out.println("Employee save failed with id=" + employee.getEmployeeId());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public Employee getById(int id) {
		String query = "select FirstName, LastName, Title, ReportsTo, BirthDate, HireDate, Address, City, State, Country, PostalCode, Phone, Fax, Email from Employee where EmployeeId = ?";
		Employee employee = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			// if(rs.next()){
			// employee = new Employee();
			// employee.setEmployeeId(id);
			// employee.setFirstName(rs.getString("FirstName"));
			// employee.setLastName(rs.getString("LastName"));
			// System.out.println("Employee Found::"+employee);
			// }else{
			// System.out.println("No Employee found with id="+id);
			// }

			try {
				employee = new Employee();
				ReflectionUtils<Employee> reflectionUtils = new ReflectionUtils<Employee>();
				reflectionUtils.setAllSetters(employee, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return employee;
	}

	@Override
	public void update(Employee employee) {
		String query = "update Employee set FirstName=?, LastName=? where EmployeeId=?";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, employee.getFirstName());
			ps.setString(2, employee.getLastName());
			ps.setInt(3, employee.getEmployeeId());
			int out = ps.executeUpdate();
			if (out != 0) {
				System.out.println("Employee updated with id=" + employee.getEmployeeId());
			} else
				System.out.println("No Employee found with id=" + employee.getEmployeeId());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void deleteById(int id) {
		String query = "delete from Employee where EmployeeId=?";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			int out = ps.executeUpdate();
			if (out != 0) {
				System.out.println("Employee deleted with id=" + id);
			} else
				System.out.println("No Employee found with id=" + id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Employee> getAll() {
		String query = "select FirstName, LastName, Title, ReportsTo, BirthDate, HireDate, Address, City, State, Country, PostalCode, Phone, Fax, Email from Employee";
		List<Employee> empList = new ArrayList<Employee>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
//			while (rs.next()) {
//				Employee employee = new Employee();
//				employee.setEmployeeId(rs.getInt("Employeeid"));
//				employee.setFirstName(rs.getString("FirstName"));
//				employee.setLastName(rs.getString("LastName"));
//				empList.add(employee);
//			}
			
			try {
				Employee employee = new Employee();
				ReflectionUtils<Employee> reflectionUtils = new ReflectionUtils<Employee>();
				empList = reflectionUtils.setAllSetters(employee, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return empList;
	}

}
