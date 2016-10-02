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
import org.springframework.web.servlet.ModelAndView;

import spring.model.Department;
import spring.model.Student;
import spring.service.DepartmentService;
import spring.service.StudentService;

@Controller
@RequestMapping(value = { "/Admission"}, method = RequestMethod.GET)
public class AdmissionController{
	
	@Autowired
	@Qualifier("studentService")
	StudentService studentService;
	
	@Autowired
	@Qualifier("departmentService")
	DepartmentService departmentService;
	
	@RequestMapping(value = { "/index"}, method = RequestMethod.GET)
	public ModelAndView welcomePage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("admission/index");
		return model;
	}
	
	//For adding a student
	@RequestMapping(value= "/addStudent", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("student") Student s){

			Department d = departmentService.getDepartmentById(s.getDepartmentId());
			s.setDepartment(d);
			this.studentService.addStudent(s);
			
		
		return "redirect:/Admission/students";
		
	}
	
	//List Students
	@RequestMapping(value = "/students", method = RequestMethod.GET)
	public String listUsers(Model model) {
		model.addAttribute("student", new Student());
		List<Student> listStudents = this.studentService.listStudents();
		model.addAttribute("listStudents", listStudents);
		return "admission/student";
	}
	
	//For removing a Student
	@RequestMapping(value= "/remove/{student_id}")
	public String removeStudent(@PathVariable("student_id") int id){

			this.studentService.removeStudent(id);
		
		return "redirect:/Admission/students";
		
	}
	
	//For editing a student - display page with Model value
	@RequestMapping(value= "/edit/{student_id}")
	public String editStudent(@PathVariable("student_id") int id, Model model){

			Student s = this.studentService.getStudentById(id);
			model.addAttribute("student", s);
		
		return "admission/student_update";
		
	}
	
	//For updating a student
	@RequestMapping(value= "/updateStudent", method = RequestMethod.POST)
	public String updateStudent(@ModelAttribute("student") Student s){

			Department d = departmentService.getDepartmentById(s.getDepartmentId());
			s.setDepartment(d);
			this.studentService.updateStudent(s);
		
		return "redirect:/Admission/students";
		
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
