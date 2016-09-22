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

import spring.model.Student;
import spring.service.StudentService;

@Controller
@RequestMapping(value = { "/HOD"}, method = RequestMethod.GET)
public class HODController{
	
	@Autowired
	@Qualifier("studentService")
	StudentService studentService;
	
	@RequestMapping(value = { "/index"}, method = RequestMethod.GET)
	public ModelAndView welcomePage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("hod/index");
		return model;
	}
	
	//For adding a student
	@RequestMapping(value= "/addStudent", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("student") Student s){

			this.studentService.addStudent(s);
		
		return "redirect:/HOD/students";
		
	}
	
		//List Students
		@RequestMapping(value = "/students", method = RequestMethod.GET)
		public String listUsers(Model model) {
			model.addAttribute("student", new Student());
			List<Student> listStudents = this.studentService.listStudents();
			model.addAttribute("listStudents", listStudents);
			return "hod/student";
		}
	
		
		// List of Departments
		@ModelAttribute("departmentList") 
		private Map populateDefaultModel() {
			 
	
			Map<String,String> departments = new HashMap<String,String>();
			departments.put("CSE", "CSE");
			departments.put("ECE", "ECE");
			departments.put("ME", "ME");
			departments.put("CIVIL", "CIVIL");
			departments.put("MBA", "MBA");
			return departments;
	
		}	
		
		//For removing a user
		@RequestMapping(value= "/remove/{student_id}")
		public String removeStudent(@PathVariable("student_id") int id){
	
				this.studentService.removeStudent(id);
			
			return "redirect:/HOD/students";
			
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
	
}
