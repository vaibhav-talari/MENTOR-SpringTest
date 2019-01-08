package spring.core.boot.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name="Student_hostel")
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long admNo;
	
	@NotEmpty(message="Name cannot be blank")
	@Size(min=5,max=25,message="Name is expected")
	private String firstName;
	
	@NotEmpty(message="Name cannot be blank")
	@Size(min=5,max=25,message="Name is expected")
	private String lastName;
	
	@NotEmpty(message="Course cannot be blank")
	private String course;
	
	@NotNull
	private double fee;
	
	@DateTimeFormat(iso=ISO.DATE)
	@Past
	private LocalDate dateOfAdmission;

	@NotNull
	private boolean isHostller;

	public long getAdmNo() {
		return admNo;
	}

	public void setAdmNo(long admNo) {
		this.admNo = admNo;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public LocalDate getDateOfAdmission() {
		return dateOfAdmission;
	}

	public void setDateOfAdmission(LocalDate dateOfAdmission) {
		this.dateOfAdmission = dateOfAdmission;
	}

	public boolean isHostller() {
		return isHostller;
	}

	public void setHostller(boolean isHostller) {
		this.isHostller = isHostller;
	}

	@Override
	public String toString() {
		return "Student [admNo=" + admNo + ", firstName=" + firstName + ", lastName=" + lastName + ", course=" + course
				+ ", fee=" + fee + ", dateOfAdmission=" + dateOfAdmission + ", isHostller=" + isHostller + "]";
	}
	
	

}
