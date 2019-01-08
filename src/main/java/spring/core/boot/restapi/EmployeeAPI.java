package spring.core.boot.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.core.boot.model.Employee;
import spring.core.boot.service.IEmployeeService;

@RestController
@RequestMapping("/emps")
@CrossOrigin
public class EmployeeAPI {
	
	@Autowired
	private IEmployeeService empService;
	
	@GetMapping
	public ResponseEntity<List<Employee>> getAll()
	{
		return new ResponseEntity<>(
				empService.getAllEmployees(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmp(
			@PathVariable("id") Long empNo)
	{
		Employee emp=empService.getEmployee(empNo);
		ResponseEntity<Employee> resp=null;
		if(emp==null)
		resp=new ResponseEntity<> (HttpStatus.NOT_FOUND);
		else
		resp=new ResponseEntity<> (emp,HttpStatus.OK);
		return resp;
	}
	
	@PostMapping
	public ResponseEntity<Employee> createEmp(@RequestBody Employee emp){
		ResponseEntity<Employee> resp=null;
		List<Employee> empResult=empService.find("mailId",emp.getMailId());
		if(empResult!=null&&empResult.size()!=0) {
			resp=new ResponseEntity<Employee>(HttpStatus.CONFLICT);
		}
		else {
			Long newID=empService.saveEmployee(emp);
			emp.setEmpNo(newID);
			resp=new ResponseEntity<Employee>(emp,HttpStatus.CREATED);
		}
		return resp;
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmp(
			@PathVariable("id") Long empNo,
			@RequestBody Employee empNew)
	{
		Employee empOld=empService.getEmployee(empNo);
		ResponseEntity<Employee> resp=null;
		if(empOld==null)
		resp=new ResponseEntity<> (HttpStatus.NOT_FOUND);
		else
		empService.saveEmployee(empNew);
		resp=new ResponseEntity<> (empNew,HttpStatus.OK);
		return resp;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmp(
			@PathVariable("id") Long empNo)
	{
		ResponseEntity<Void> resp=null;
		if(empService.deleteEmployee(empNo))
		resp=new ResponseEntity<> (HttpStatus.OK);
		else
		resp=new ResponseEntity<> (HttpStatus.NOT_FOUND);
		return resp;
	}

}
