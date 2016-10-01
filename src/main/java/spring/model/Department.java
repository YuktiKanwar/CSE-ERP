package spring.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "departments")
public class Department {

	private int id;
	private String name;
	private Set<Student> student = new HashSet<Student>(0);
	private Set<Subject> subject= new HashSet<Subject>(0);
	
	public Department() {
	}

	public Department(int id,String name) {
		this.id = id;
		this.name = name;
	}
	
	@Id
	@SequenceGenerator(name="seq-gen",sequenceName="departments_id_seq", initialValue=100)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="seq-gen")
	@Column(name = "id", unique= true, nullable= false)
	public int getId(){
		return this.id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	@Column(name = "name",
		nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(fetch = FetchType.LAZY)
	public Set<Student> getStudent() {
		return this.student;
	}

	public void setStudent(Set<Student> student) {
		this.student = student;
	}
	
	@OneToMany(fetch = FetchType.LAZY)
	public Set<Subject> getSubject() {
		return this.subject;
	}

	public void setSubject(Set<Subject> subject) {
		this.subject = subject;
	}
}
