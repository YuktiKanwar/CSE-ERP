package spring.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "subjects")
public class Subject {

	private int id;
	private String name;
	private int semester;
	private Department department= new Department();
	
	public Subject() {
	}

	public Subject(int id,String name, int semester, Department department) {
		this.id = id;
		this.name = name;
		this.semester = semester;
		this.department = department;
	}
	
	@Id
	@SequenceGenerator(name="seq-gen",sequenceName="subjects_id_seq", initialValue=100)
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
	
	@Column(name = "semester",
			nullable = false, length = 45)
		public int getSemester() {
			return this.semester;
		}

		public void setSemester(int semester) {
			this.semester = semester;
		}
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id", nullable = false)
	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
}
