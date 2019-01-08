package spring.core.boot.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name="spring_employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long empNo;
	
	@NotEmpty(message="Name cannot be blank")
	@Size(min=5,max=25,message="Name is expected")
	private String empName;
	
	@DateTimeFormat(iso=ISO.DATE)
	@Past
	private LocalDate dateofBirth;
	
	@NotNull
	@Min(value=12000,message="Basic cannot be less than 12000")
	@Max(value=45000,message="Basic cannot be more than 45000")
	private double basic;
	
	@NotNull(message="Email is required")
	@Email(message="Invalid Mail ID")
	private String mailId;

	public long getEmpNo() {
		return empNo;
	}

	public void setEmpNo(long empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public LocalDate getDateofBirth() {
		return dateofBirth;
	}

	public void setDateofBirth(LocalDate dateofBirth) {
		this.dateofBirth = dateofBirth;
	}

	public double getBasic() {
		return basic;
	}

	public void setBasic(double basic) {
		this.basic = basic;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	@Override
	public String toString() {
		return "Employee [empNo=" + empNo + ", empName=" + empName + ", dateofBirth=" + dateofBirth + ", basic=" + basic
				+ ", mailId=" + mailId + "]";
	}
	
	public Employee() {}
	
	public Employee(String name,String mailId) {
		setEmpName(name);
		setMailId(mailId);
		setDateofBirth(LocalDate.now().minusDays(2));
		setBasic(35400.0);
	}
	
	
}
