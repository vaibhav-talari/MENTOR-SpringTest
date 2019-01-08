package spring.core.boot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.core.boot.model.Employee;

@Repository
public interface IEmployeeRepo extends JpaRepository<Employee,Long> {

	public List<Employee> findAllByEmpName(String empName);//for more than one rec
	public Employee findByMailId(String mailId);
}
