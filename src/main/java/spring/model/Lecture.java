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
@Table(name = "lectures")
public class Lecture {

	private int id;
	private Subject subject;
	private User user;
	
	@Transient
	private int subjectId;
	@Transient
	private int userId;
	
	public Lecture() {
	}

	public Lecture(int id,Subject subject, User user) {
		this.id = id;
		this.subject = subject;
		this.user = user;
	}
	
	@Id
	@SequenceGenerator(name="seq-gen",sequenceName="lectures_id_seq", initialValue=100)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="seq-gen")
	@Column(name = "id", unique= true, nullable= false)
	public int getId(){
		return this.id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subject_id", nullable = false)
	public Subject getSubject() {
		return this.subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public void setUserId(int id) {
		this.userId = id;
	}
	
	@Transient
	public int getUserId() {
		return this.userId;
	}
	public void setSubjectId(int id) {
		this.subjectId = id;
	}
	
	@Transient
	public int getSubjectId() {
		return this.subjectId;
	}
}
