package spring.model;

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
@Table(name = "transports")
public class Transport {
	
	private int id;
	private int routeNumber;
	private Student student;
	@Transient
	public int studentId;

	public Transport() {
	}

	public Transport(int id,int routeNumber, Student student, int studentId) {
		this.id = id;
		this.routeNumber=routeNumber;
		this.student=student;
	}

	@Id
	@SequenceGenerator(name="seq-gen",sequenceName="transports_id_seq", initialValue=100)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="seq-gen")
	@Column(name = "id", unique= true, nullable= false)
	public int getId(){
		return this.id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	@Column(name = "route_number",
		nullable = false, length = 45)
	public int getRouteNumber() {
		return this.routeNumber;
	}

	public void setRouteNumber(int routeNumber) {
		this.routeNumber = routeNumber;
	}


	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name = "student_id", nullable = false)
		public Student getStudent() {
			return this.student;
		}

		public void setStudent(Student student) {
			this.student = student;
		}

		

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
	@Transient
	public int getStudentId() {
		return this.studentId;
	}


}
