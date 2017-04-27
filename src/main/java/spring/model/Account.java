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
@Table(name = "accounts")
public class Account {
	
	private int id;
	private int totalFee;
	private int submittedFee;
	private Student student;
	@Transient
	public int studentId;

	public Account() {
	}

	public Account(int id,int total_fee,int submitted_fee, Student student, int studentId) {
		this.id = id;
		this.totalFee = total_fee;
		this.submittedFee=submitted_fee;
		this.student=student;
	}

	@Id
	@SequenceGenerator(name="seq-gen",sequenceName="accounts_id_seq", initialValue=100)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="seq-gen")
	@Column(name = "id", unique= true, nullable= false)
	public int getId(){
		return this.id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	@Column(name = "total_fee",
		nullable = false, length = 45)
	public int getTotalFee() {
		return this.totalFee;
	}

	public void setTotalFee(int total_fee) {
		this.totalFee = total_fee;
	}
	
	@Column(name = "submitted_fee",
			nullable = false, length = 45)
		public int getSubmittedFee() {
			return this.submittedFee;
		}

		public void setSubmittedFee(int submitted_fee) {
			this.submittedFee = submitted_fee;
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
