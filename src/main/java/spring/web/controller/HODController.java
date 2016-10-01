package spring.web.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.model.Department;
import spring.model.Lecture;
import spring.model.Student;
import spring.model.Subject;
import spring.model.User;
import spring.model.UserRole;
import spring.service.StudentService;
import spring.service.SubjectService;
import spring.service.UserCRUDService;
import spring.service.DepartmentService;
import spring.service.LectureService;

@Controller
@RequestMapping(value = { "/HOD"}, method = RequestMethod.GET)
public class HODController{
	
	@Autowired
	@Qualifier("studentService")
	StudentService studentService;
	
	@Autowired
	@Qualifier("departmentService")
	DepartmentService departmentService;
	
	@Autowired
	@Qualifier("lectureService")
	LectureService lectureService;

	@Autowired
	@Qualifier("userCRUDService")
	UserCRUDService userService;
	
	@Autowired
	@Qualifier("subjectService")
	SubjectService subjectService;
	
	@RequestMapping(value = { "/index"}, method = RequestMethod.GET)
	public ModelAndView welcomePage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("hod/index");
		return model;
	}
	
	//For adding a student
	@RequestMapping(value= "/addStudent", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("student") Student s){

			Department d = departmentService.getDepartmentById(s.getDepartmentId());
			s.setDepartment(d);
			this.studentService.addStudent(s);
			
		
		return "redirect:/HOD/students";
		
	}
	
	//For adding a lecture
	@RequestMapping(value= "/addLecture", method = RequestMethod.POST)
	public String addLecture(@ModelAttribute("lecture") Lecture l){

			Subject s = subjectService.getSubjectById(l.getSubjectId());
			l.setSubject(s);
			User u = userService.getUserById(l.getUserId());
			l.setUser(u);
			this.lectureService.addLecture(l);
			
		
		return "redirect:/HOD/lectures";
		
	}
	
		//List Students
		@RequestMapping(value = "/students", method = RequestMethod.GET)
		public String listUsers(Model model) {
			model.addAttribute("student", new Student());
			List<Student> listStudents = this.studentService.listStudents();
			model.addAttribute("listStudents", listStudents);
			return "hod/student";
		}
		
		//List Lectures
		@RequestMapping(value = "/lectures", method = RequestMethod.GET)
		public String listLectures(Model model) {
			model.addAttribute("lecture", new Lecture());
			List<Lecture> listLectures = this.lectureService.listLectures();
			model.addAttribute("listLectures", listLectures);
			List<User> users = userService.listUsers();
			Map<Integer,String> faculties = new HashMap<Integer,String>();
			for (int i = 1; i < users.size(); i++) {
				
				    	faculties.put(users.get(i).getId(), users.get(i).getUsername());
			
			}
			
			List<Subject> subjectList = subjectService.listSubjects();
			model.addAttribute("facultyList", faculties);
			model.addAttribute("subjectList", subjectList);
			return "hod/lecture";
		}
	
		
		//For removing a user
		@RequestMapping(value= "/remove/{student_id}")
		public String removeStudent(@PathVariable("student_id") int id){
	
				this.studentService.removeStudent(id);
			
			return "redirect:/HOD/students";
			
		}
		
		//For removing a lecture
		@RequestMapping(value= "/remove/lecture/{lecture_id}")
		public String removeLecture(@PathVariable("lecture_id") int id){
	
				this.lectureService.removeLecture(id);
			
			return "redirect:/HOD/lectures";
			
		}
		
		//For editing a student - display page with Model value
		@RequestMapping(value= "/edit/{student_id}")
		public String editStudent(@PathVariable("student_id") int id, Model model){
	
				Student s = this.studentService.getStudentById(id);
				model.addAttribute("student", s);
			
			return "hod/student_update";
			
		}
		
		//For updating a student
		@RequestMapping(value= "/updateStudent", method = RequestMethod.POST)
		public String updateStudent(@ModelAttribute("student") Student s){
	
				this.studentService.updateStudent(s);
			
			return "redirect:/HOD/students";
			
		}
		
		// List of Departments for view
		@ModelAttribute("departmentList") 
		private Map<Integer,String> populateDefaultModel() {
			 
	
			Map<Integer,String> departments = new HashMap<Integer,String>();
			for (int i = 1; i <= 5; i++) {
				Department department = this.departmentService.getDepartmentById(i);
				departments.put(department.getId(), department.getName());
			}
			return departments;
	
		}		
}
