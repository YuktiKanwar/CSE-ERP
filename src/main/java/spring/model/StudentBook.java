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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "students_books")
public class StudentBook {
	
	private int id;
	private Student student;
	private Book book;
	@Transient
	private int bookId;
	@Transient
	private int studentId;

	public StudentBook() {
	}

	public StudentBook(int id,int bookId,int studentId, Student student,Book book) {
		this.id = id;
		this.bookId = book.getId();
		this.studentId=student.getId();
		this.student=student;
		this.book=book;
	}

	@Id
	@SequenceGenerator(name="seq-gen",sequenceName="students_books_id_seq", initialValue=100)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="seq-gen")
	@Column(name = "id", unique= true, nullable= false)
	public int getId(){
		return this.id;
	}
	public void setId(int id)
	{
		this.id = id;
	}

	
	@OneToOne(fetch= FetchType.LAZY)
	@JoinColumn(name = "book_id", nullable = false)
		public Book getBook() {
			return this.book;
		}

		public void setBook(Book book) {
			this.book = book;
		}
	
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name = "student_id", nullable = false)
		public Student getStudent() {
			return this.student;
		}

		public void setStudent(Student student) {
			this.student = student;
		}

		
	public void setBookId(int id) {
		this.bookId = id;
	}
	
	public void setStudentId(int id) {
		this.studentId = id;
	}
	
	@Transient
	public int getBookId() {
		return this.bookId;
	}
	@Transient
	public int getStudentId() {
		return this.studentId;
	}


}
