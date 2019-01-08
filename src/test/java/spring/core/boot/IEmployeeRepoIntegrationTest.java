package spring.core.boot;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import spring.core.boot.dao.IEmployeeRepo;
import spring.core.boot.model.Employee;

@RunWith(SpringRunner.class)//spring framework support
@DataJpaTest//to get hibernate and mapping done
@AutoConfigureTestDatabase//to configure the h2 database
public class IEmployeeRepoIntegrationTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private IEmployeeRepo empRepo;
	
	private Employee[] emps;
	
	@Before
	public void setUp() {
		emps=new Employee[] {
				new Employee("Ravi Kumar","ravi.Kumar@mail.com"),
				new Employee("Ravi Kumar","ravi.Kumar1234@mail.com"),
				new Employee("Seema","seema@mail.com")
		};
		for(Employee e:emps)
			entityManager.persist(e);
	}
	
	@After
	public void tearDown() {
		for (Employee e:emps)
			entityManager.remove(e);
	}
	
	@Test
	public void whenFindAllByEmpName_returnMathingEmployeeObject() {
		List<Employee> actual=empRepo.findAllByEmpName("Ravi Kumar");
		List<Employee> expected=Arrays.asList(emps[0],emps[1]);
		assertThat(actual).isEqualTo(expected);

	}
	
	@Test
	public void  whenFindByMailId_returnMatchingEmployee()
	{
		Employee actual=empRepo.findByMailId(emps[2].getMailId());
		Employee expected =emps[2];
		assertThat(actual).isEqualTo(expected);

	}
	
	
	
}
