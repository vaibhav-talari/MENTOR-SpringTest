package spring.core.boot.service;

import java.util.List;

import spring.core.boot.model.Student;

public interface IStudentService {
	
	public Long saveStudent(Student student);
	public boolean deleteStudent(long admNo);
	public Student getStudent(long admNo);
	public List<Student> getAllStudents();

}
