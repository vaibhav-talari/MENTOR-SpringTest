package spring.core.boot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.core.boot.model.Student;

@Repository
public interface IStudentRepo extends JpaRepository<Student, Long> {

}
