package com.sample.model.dao;

import java.util.List;

import com.sample.model.domain.Employee;

//CRUD operations
public interface IEmployeeDAO {

	// Create
	public void save(Employee employee);

	// Read
	public Employee getById(int id);
	
	// Update
	public void update(Employee employee);
	
	// Delete
	public void deleteById(int id);

	// Get All
	public List<Employee> getAll();
}
