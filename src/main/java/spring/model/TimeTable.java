package spring.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "timetables")
public class TimeTable{

	private int id;
	private String day;
	private int time;
	private Lecture lecture = new Lecture();
	@Transient
	private int lectureId;
	
	public TimeTable() {
	}

	public TimeTable(int id,Lecture lecture, String day, int time) {
		this.id = id;
		this.lecture = lecture;
		this.day = day;
		this.time = time;
	}
	
	@Id
	@SequenceGenerator(name="seq-gen",sequenceName="timetables_id_seq", initialValue=100)
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
	@JoinColumn(name = "lecture_id", nullable = false)
	public Lecture getLecture() {
		return this.lecture;
	}

	public void setLecture(Lecture lecture) {
		this.lecture = lecture;
	}
	
	@Column(name = "day",
			nullable = false, length = 45)
		public String getDay() {
			return this.day;
		}

		public void setDay(String day) {
			this.day = day;
		}
	
	@Column(name = "time",
			nullable = false, length = 45)
		public int getTime() {
			return this.time;
		}

		public void setTime(int time) {
			this.time = time;
		}
		
	public void setLectureId(int id) {
		this.lectureId = id;
	}
	
	@Transient
	public int getLectureId() {
		return this.lectureId;
	}
}
