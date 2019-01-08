package spring.core.boot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.core.boot.dao.IStudentRepo;
import spring.core.boot.model.Student;

@Service
public class StudentServiceImpl implements IStudentService {
	
	@Autowired
	private IStudentRepo studentRepo;

	@Override
	public Long saveStudent(Student student) {
		Student studentSaved=studentRepo.save(student);
		return studentSaved==null?-1:studentSaved.getAdmNo();
	}

	@Override
	public boolean deleteStudent(long admNo) {
		boolean isDeleted=false;
		if(studentRepo.existsById(admNo))
		{
			studentRepo.deleteById(admNo);
			isDeleted=true;
		}
		return isDeleted;
	}

	@Override
	public Student getStudent(long admNo) {
		Optional<Student> studentData= studentRepo.findById(admNo);
		return studentData.isPresent()?studentData.get():null;
	}

	@Override
	public List<Student> getAllStudents() {
		
		return studentRepo.findAll();
	}

}
