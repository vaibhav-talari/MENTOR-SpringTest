package spring.core.boot.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.core.boot.model.Student;
import spring.core.boot.service.IStudentService;

@RestController
@RequestMapping("/student")
public class StudentAPI {
	
	@Autowired
	private IStudentService studentService;
	
	@GetMapping
	public ResponseEntity<List<Student>> getAll()
	{
		return new ResponseEntity<>(
				studentService.getAllStudents(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudent(
			@PathVariable("id") Long admNo)
	{
		Student student=studentService.getStudent(admNo);
		ResponseEntity<Student> resp=null;
		if(student==null)
		resp=new ResponseEntity<> (HttpStatus.NOT_FOUND);
		else
		resp=new ResponseEntity<> (student,HttpStatus.OK);
		return resp;
	}
	
	@PostMapping
	public ResponseEntity<Student> createStudent(@RequestBody Student student){
		ResponseEntity<Student> resp=null;
		Long newID=studentService.saveStudent(student);
		student.setAdmNo(newID);
		resp=new ResponseEntity<Student>(student,HttpStatus.CREATED);
		return resp;
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Student> updateEmp(
			@PathVariable("id") Long admNo,
			@RequestBody Student studentNew)
	{
		Student studentOld=studentService.getStudent(admNo);
		ResponseEntity<Student> resp=null;
		if(studentOld==null) {
		resp=new ResponseEntity<> (HttpStatus.NOT_FOUND);}
		else {		
			studentOld.setFirstName(studentNew.getFirstName());
			studentOld.setLastName(studentNew.getLastName());
			studentOld.setFee(studentNew.getFee());
			studentOld.setCourse(studentNew.getCourse());
			studentOld.setDateOfAdmission(studentNew.getDateOfAdmission());
			studentOld.setHostller(studentNew.isHostller());

			studentService.saveStudent(studentOld);
		resp=new ResponseEntity<> (studentOld,HttpStatus.OK);
		}
		return resp;

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmp(
			@PathVariable("id") Long admNo)
	{
		ResponseEntity<Void> resp=null;
		if(studentService.deleteStudent(admNo))
		resp=new ResponseEntity<> (HttpStatus.OK);
		else
		resp=new ResponseEntity<> (HttpStatus.NOT_FOUND);
		return resp;
	}

}
