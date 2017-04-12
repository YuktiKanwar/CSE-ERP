package spring.web.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import spring.model.Department;
import spring.model.Student;
import spring.model.StudentBook;
import spring.model.Subject;
import spring.model.Book;
import spring.service.BookService;
import spring.service.DepartmentService;
import spring.service.StudentBookService;
import spring.service.StudentService;
import spring.service.SubjectService;

@Controller
@RequestMapping(value = { "/Library"}, method = RequestMethod.GET)
public class LibraryController{
	
	@Autowired
	@Qualifier("bookService")
	BookService bookService;
	
	@Autowired
	@Qualifier("departmentService")
	DepartmentService departmentService;

	@Autowired
	@Qualifier("studentBookService")
	StudentBookService studentBookService;
	
	@Autowired
	@Qualifier("subjectService")
	SubjectService subjectService;

	@Autowired
	@Qualifier("studentService")
	StudentService studentService;
	
	@RequestMapping(value = { "/index"}, method = RequestMethod.GET)
	public ModelAndView welcomePage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("library/index");
		return model;
	}
	
	//For adding a book
	@RequestMapping(value= "/addBook", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("book") Book b, Model model){
			Department d = this.departmentService.getDepartmentById(b.getDepartmentId());
			b.setDepartment(d);
			Subject s = this.subjectService.getSubjectById(b.getSubjectId());
			b.setSubject(s);
			this.bookService.addBook(b);
			List<Book> listBooks = this.bookService.listBooks();
			model.addAttribute("book", new Book());
			model.addAttribute("listBooks", listBooks);
			
			Map<Integer,String> departments = new HashMap<Integer,String>();
			for (int i = 1; i <= 6; i++) {
				Department department = this.departmentService.getDepartmentById(i);
				departments.put(department.getId(), department.getName());
			}
			model.addAttribute("departmentList", departments);
			
			return "library/book";
	}
	
	//For editing a book - display page with Model value
	@RequestMapping(value= "/edit/{book_id}")
	public String editBook(@PathVariable("book_id") int id, Model model){

			Book b = this.bookService.getBookById(id);
			model.addAttribute("book", b);
			Map<Integer,String> departments = new HashMap<Integer,String>();
			for (int i = 1; i <= 6; i++) {
				Department department = this.departmentService.getDepartmentById(i);
				departments.put(department.getId(), department.getName());
			}
			model.addAttribute("departmentList", departments);
		
		return "library/book_update";
		
	}
	
	//For updating a book
	@RequestMapping(value= "/updateBook", method = RequestMethod.POST)
	public String updateStudent(@ModelAttribute("book") Book b){

			Department d = departmentService.getDepartmentById(b.getDepartmentId());
			b.setDepartment(d);
			Subject s = this.subjectService.getSubjectById(b.getSubjectId());
			b.setSubject(s);
			this.bookService.updateBook(b);
		
		return "redirect:/Library/books";
		
	}
	
	//List Books
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public String listUsers(Model model) {
		model.addAttribute("book", new Book());
		List<Book> listBooks= this.bookService.listBooks();
		model.addAttribute("listBooks", listBooks);
		
		Map<Integer,String> departments = new HashMap<Integer,String>();
		for (int i = 1; i <= 6; i++) {
			Department department = this.departmentService.getDepartmentById(i);
			departments.put(department.getId(), department.getName());
		}
		model.addAttribute("departmentList", departments);
		return "library/book";
	}
	
	//Issue Books
	@RequestMapping(value = "/issue", method = RequestMethod.GET)
	public String listBooksForIssue(Model model, @RequestParam(value = "issued",	required = false) String issued) {
		if (issued != null) {
			model.addAttribute("message", "Book Issued");
		}
		List<Book> listBooks= this.bookService.listBooks();
		model.addAttribute("listBooks", listBooks);
		return "library/issue";
	}
	
	//Issue Book - List Students
	@RequestMapping(value= "/issue/{book_id}")
	public String issueBookStep1(@PathVariable("book_id") int id, Model model){

			Book b = this.bookService.getBookById(id);
			model.addAttribute("book", b);
			List<Student> studentList = this.studentService.listStudents();
			model.addAttribute("studentList", studentList);
		return "library/issue_students";
		
	}

	
	//Issue Book - List IssuedBooks
	@RequestMapping(value= "/issue/{book_id}/{student_id}")
	public String issueBookStep2(@PathVariable("book_id") int book_id,@PathVariable("student_id") int student_id, Model model){
			
			Book b = this.bookService.getBookById(book_id);
			int count = b.getCount();
			count = count -1;
			b.setCount(count);
			this.bookService.updateBook(b);
			Student s = this.studentService.getStudentById(student_id);
			StudentBook sb = new StudentBook();
			sb.setBook(b);
			sb.setStudent(s);
			sb.setBookId(b.getId());
			sb.setStudentId(s.getId());
			this.studentBookService.addStudentBook(sb);
			List<StudentBook> studentBooks = this.studentBookService.listStudentBooks();
			model.addAttribute("studentBooks", studentBooks);
		return "redirect:/Library/issue?issued=true";
		
	}
	
	//Issue Book - List of issued Students
	@RequestMapping(value= "/issued_students")
	public String issuedStudents(Model model, @RequestParam(value = "removed",	required = false) String removed){
		if (removed != null) {
			model.addAttribute("message", "Book Retrieved.");
		}
		List<StudentBook> studentBooks = this.studentBookService.listStudentBooks();
		model.addAttribute("studentBooks", studentBooks);
		return "library/issued_students";
		
	}
	
	//For removing a Book
	@RequestMapping(value= "/remove/{studentBook_id}")
	public String removeBook(@PathVariable("studentBook_id") int id){
		StudentBook sb = this.studentBookService.getStudentBookById(id);
		Book b = sb.getBook();
		int count = b.getCount();
		count = count+1;
		b.setCount(count);
		this.bookService.updateBook(b);
		this.studentBookService.removeStudentBook(id);
		return "redirect:/Library/issued_students?removed=true";
		
	}
	
	// List of Departments for view
	@ModelAttribute("subjectList") 
	private Map<Integer,String> populateDefaultModel() {
		 
		int subjectSize = this.subjectService.listSubjects().size();
		Map<Integer,String> subjects = new HashMap<Integer,String>();
		for (int i = 4; i <= subjectSize ; i++) {
			Subject subject= this.subjectService.getSubjectById(i);
			subjects.put(subject.getId(), subject.getName());
		}
		return subjects;

	}	

}
