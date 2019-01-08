package spring.core.boot.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.core.boot.dao.IEmployeeRepo;
import spring.core.boot.model.Employee;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private IEmployeeRepo empRepo;
	@Override
	public Long saveEmployee(Employee emp) {
		Employee empSaved=empRepo.save(emp);
		return empSaved==null?-1:empSaved.getEmpNo();
	}

	@Override
	public boolean deleteEmployee(long empId) {
		boolean isDeleted=false;
		if(empRepo.existsById(empId))
		{
			empRepo.deleteById(empId);
			isDeleted=true;
		}
		return isDeleted;
	}

	@Override
	public Employee getEmployee(long empId) {
		Optional<Employee> empData= empRepo.findById(empId);
		return empData.isPresent()?empData.get():null;
	}

	@Override
	public List<Employee> getAllEmployees() {
		return empRepo.findAll();
	}

	@Override
	public List<Employee> find(String field, String value) {
		List<Employee> records=null;
		if("empName".equals(field))
			records=empRepo.findAllByEmpName(value);
		else if("mailId".equals(field)) {
			Employee e=empRepo.findByMailId(value);
			if(e!=null)
			records=Collections.singletonList(e);
		}
		return records;
	}
	}


