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
@Table(name = "books")
public class Book {
	
	private int id;
	private String title;
	private String desc;
	private int count;
	private Department department;
	private int semester;
	private Subject subject;
	@Transient
	private int departmentId;
	@Transient
	private int subjectId;

	public Book() {
	}

	public Book(int id,String title, String desc,int count, Department department, int sem, Subject subject) {
		this.id = id;
		this.title = title;
		this.desc=desc;
		this.count=count;
		this.department=department;
		this.semester=sem;
		this.subject= subject;
	}

	@Id
	@SequenceGenerator(name="seq-gen",sequenceName="books_id_seq", initialValue=100)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="seq-gen")
	@Column(name = "id", unique= true, nullable= false)
	public int getId(){
		return this.id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	@Column(name = "title",
		nullable = false, length = 45)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "description",
			nullable = false)
		public String getDesc() {
			return this.desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

	@Column(name = "count", unique = true,
			nullable = false, length = 45)
		public int getCount() {
			return this.count;
		}

		public void setCount(int count) {
			this.count = count;
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
	
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name = "subject_id", nullable = false)
		public Subject getSubject() {
			return this.subject;
		}

		public void setSubject(Subject subject) {
			this.subject = subject;
		}

		
	public void setDepartmentId(int id) {
		this.departmentId = id;
	}
	
	public void setSubjectId(int id) {
		this.subjectId = id;
	}
	
	@Transient
	public int getDepartmentId() {
		return this.departmentId;
	}
	@Transient
	public int getSubjectId() {
		return this.subjectId;
	}


}
