package spring.core.boot;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import spring.core.boot.model.Employee;
import spring.core.boot.restapi.EmployeeAPI;
import spring.core.boot.service.IEmployeeService;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeAPI.class)
public class EmployeeAPIUnitTest {
	
	@MockBean
	private IEmployeeService empService;
	
	@Autowired
	private MockMvc mockMvc;//act like a dummy client
	
	@Test
	public void whenGetAllEmployeesRequest_returnAJSONArray() throws Exception
	{
		Employee[] emps = new Employee[] {
				new Employee("Ravi Kumar", "ravi.kumar@iiht.com"),				
				new Employee("Ravi Kumar", "ravi.kumar123@iiht.com"), 				
				new Employee("Seema", "seema@iiht.com") };		
		Mockito.when(empService.getAllEmployees())
		.thenReturn(Arrays.asList(emps));
		
		mockMvc.perform(get("/emps").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].empName",is(emps[0].getEmpName())));
		
	}
	
	@Test
	public void whenGetEmployeesByIdRequest_returnAJSONArray() throws Exception
	{
		Employee emp = new Employee("Seema", "seema@iiht.com") ;		
		Mockito.when(empService.getEmployee(0))
		.thenReturn(emp);
		
		mockMvc.perform(get("/emps/0").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.empName",is(emp.getEmpName())));
		
	}
	
	@Test
	public void whenEmployeePosted() throws Exception{
		String jsonString="{\"empNo\":0,\"empName\":\"Vamsy Kiran\",\"dateOfBirth\":"+
				"\"2018-11-12\",\"basic\":34563.0,\"mailId\":\"a.vamc.it@gmail.com\"}";
		
		Mockito.when(empService.find("mailId","a.vamc.it@gmail.com"))
		.thenReturn(null);
		
		Mockito.when(empService.saveEmployee(Mockito.any()))
		.thenReturn(1L);
		
		RequestBuilder req = MockMvcRequestBuilders
				.post("/emps")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonString);
		
		mockMvc.perform(req)
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.empNo",is(1)))
		.andExpect(jsonPath("$.empName",is("Vamsy Kiran")));
		
	}

}
