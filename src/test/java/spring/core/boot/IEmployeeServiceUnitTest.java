package spring.core.boot;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import spring.core.boot.dao.IEmployeeRepo;
import spring.core.boot.model.Employee;
import spring.core.boot.service.EmployeeServiceImpl;
import spring.core.boot.service.IEmployeeService;

@RunWith(SpringRunner.class)
public class IEmployeeServiceUnitTest {

	@TestConfiguration
	static class EmployeeServiceImplUnitTestConfig {
		@Bean
		public IEmployeeService employeeService() {
			return new EmployeeServiceImpl();
		}
	}

	@Autowired
	private IEmployeeService empService;
	@MockBean
	private IEmployeeRepo empRepo;

	private Employee[] emps;

	@Before	public void setUp() {		
		emps = new Employee[] { 				
				new Employee("Ravi Kumar", "ravi.kumar@iiht.com"),				
				new Employee("Ravi Kumar", "ravi.kumar123@iiht.com"), 				
				new Employee("Seema", "seema@iiht.com") };				
		Mockito.when(empRepo.findAllByEmpName(emps[0].getEmpName()))
		.thenReturn(Arrays.asList(emps[0],emps[1]));				
		Mockito.when(empRepo.findByMailId(emps[2].getMailId()))		
		.thenReturn(emps[2]);		
	
	}
	@Test
	public void whenfind_withName() {
		List<Employee> actual = empService.find("empName", "Ravi Kumar");
		List<Employee> expected = Arrays.asList(emps[0],emps[1]);
		assertThat(actual).isEqualTo(expected);
	}
	
	@Test
	public void whenfind_givenMailId() {
		List<Employee> actual = empService.find("mailId", "seema@iiht.com");
		List<Employee> expected = Arrays.asList(emps[2]);
		assertThat(actual).isEqualTo(expected);
	}
	}


