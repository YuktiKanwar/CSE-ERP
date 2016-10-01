package spring.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "students")
public class Student {
	
	private int id;
	private String firstName;
	private String lastName;
	private String rollNo;
	private int semester;
	private Department department;
	private String email;
	@Transient
	private int departmentId;

	public Student() {
	}

	public Student(int id,String firstName, String lastName, String rollNo, int semester, Department department, String email) {
		this.id = id;
		this.firstName = firstName;
		this.lastName=lastName;
		this.rollNo=rollNo;
		this.semester=semester;
		this.department=department;
		this.email= email;
	}


	@Id
	@SequenceGenerator(name="seq-gen",sequenceName="students_id_seq", initialValue=100)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="seq-gen")
	@Column(name = "id", unique= true, nullable= false)
	public int getId(){
		return this.id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	@Column(name = "firstName",
		nullable = false, length = 45)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column(name = "lastName",
			nullable = false, length = 45)
		public String getLastName() {
			return this.lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

	@Column(name = "roll_no", unique = true,
			nullable = false, length = 45)
		public String getRollNo() {
			return this.rollNo;
		}

		public void setRollNo(String rollNo) {
			this.rollNo = rollNo;
		}

	@Column(name = "semester", unique = true,
			nullable = false, length = 45)
		public int getSemester() {
			return this.semester;
		}

		public void setSemester(int semester) {
			this.semester = semester;
		}
		
	
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name = "department_id", nullable = false)
		public Department getDepartment() {
			return this.department;
		}

		public void setDepartment(Department department) {
			this.department = department;
		}
	@Column(name = "email",
			nullable = false, length = 45)
		public String getEmail() {
			return this.email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
	
	public void setDepartmentId(int id) {
		this.departmentId = id;
	}
	
	@Transient
	public int getDepartmentId() {
		return this.departmentId;
	}


}
