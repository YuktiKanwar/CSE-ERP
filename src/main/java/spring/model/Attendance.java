package spring.model;

import java.util.Date;
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
@Table(name = "attendances")
public class Attendance {

	private int id;
	private int timetable_id;
	private int student_id;
	private Date date;
	private String value;
	
	public Attendance() {
	}

	public Attendance(int id, int timetable_id, int student_id, Date date, String value) {
		this.id = id;
		this.timetable_id = timetable_id;
		this.student_id = student_id;
		this.date = date;
		this.value = value;
	}
	
	public Attendance(int timetable_id, int student_id, Date date, String value) {
		this.timetable_id = timetable_id;
		this.student_id = student_id;
		this.date = date;
		this.value = value;
	}
	
	@Id
	@SequenceGenerator(name="seq-gen",sequenceName="attendances_id_seq", initialValue=100)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="seq-gen")
	@Column(name = "id", unique= true, nullable= false)
	public int getId(){
		return this.id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	@Column(name = "timetable_id", unique= true, nullable= false)
	public int getTimetable_id(){
		return this.timetable_id;
	}
	public void setTimetable_id(int timetable_id)
	{
		this.timetable_id = timetable_id;
	}
	@Column(name = "student_id", unique= true, nullable= false)
	public int getStudent_id(){
		return this.student_id;
	}
	public void setStudent_id(int student_id)
	{
		this.student_id = student_id;
	}
	@Column(name = "value",
		nullable = false, length = 45)
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	@Column(name = "date",
			nullable = false)
		public Date getDate() {
			return this.date;
		}

		public void setDate(Date date) {
			this.date = date;
		}
	
}
