package spring.core.boot.service;

import java.util.List;

import spring.core.boot.model.Employee;

public interface IEmployeeService {
	
	public Long saveEmployee(Employee emp);
	public boolean deleteEmployee(long empId);
	public Employee getEmployee(long empId);
	public List<Employee> getAllEmployees();
	public List<Employee> find(String field,String value);

}
