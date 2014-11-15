package com.sample.test;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sample.model.dao.impl.EmployeeDAO;
import com.sample.model.dao.impl.EmployeeJDBCTemplateDAO;
import com.sample.model.domain.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/applicationContext.xml")
public class MainTest {

	@Autowired
	EmployeeDAO employeeDAO;

	@Autowired
	EmployeeJDBCTemplateDAO employeeJDBCTemplateDAO;

	// @Ignore
	@Test
	public void testEmployeeDAO() {
		Employee employee = this.employeeDAO.getById(1);
		System.out.println(employee.getFirstName());
		System.out.println(employee.getEmail());

		// employee = new Employee();
		// employee.setFirstName("TEST_FIRSTNAME");
		// employee.setLastName("TEST_LASTNAME");
		// employee.setTitle("TEST_TITLE");
		// this.employeeDAO.save(employee);

		// employee.setEmployeeId(9);
		// employee.setFirstName("FIRSTNAME_UPDATED");
		// employee.setLastName("LASTNAME_UPDATED");
		// employee.setTitle("TITLE_UPDATED");
		// this.employeeDAO.update(employee);

		this.employeeDAO.getAll();
	}

	@Ignore
	@Test
	public void testEmployeeJDBCTemplateDAO() {
		Employee employee = this.employeeJDBCTemplateDAO.getById(1);
		System.out.println(employee.getFirstName());
		System.out.println(employee.getEmail());
	}

}
