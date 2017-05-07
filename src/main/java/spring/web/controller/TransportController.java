package spring.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.model.Account;
import spring.model.Student;
import spring.model.Transport;
import spring.service.AccountService;
import spring.service.StudentService;
import spring.service.TransportService;

@Controller
@RequestMapping(value = { "/Transport"}, method = RequestMethod.GET)
public class TransportController{
	@Autowired
	@Qualifier("studentService")
	StudentService studentService;
	
	@Autowired
	@Qualifier("transportService")
	TransportService transportService;
	
	@RequestMapping(value = { "/index"}, method = RequestMethod.GET)
	public ModelAndView welcomePage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("transport/index");
		return model;
	}
	
	//Issue Transport - List Students
	@RequestMapping(value= "/students")
	public String issueAccount1(Model model){
			List<Student> studentList = this.studentService.listStudents();
			model.addAttribute("listStudents", studentList);
		return "transport/students";
		
	}
	
	//For displaying transport detail of a student
	@RequestMapping(value= "/view/{student_id}")
	public String studentFeeDetail(@PathVariable("student_id") int student_id, Model model){

			Transport t = this.transportService.getTransportByStudentId(student_id);
			model.addAttribute("studentId",student_id);
			model.addAttribute("transport", t);
			
		return "transport/view_fee";
		
	}
	
	//For adding Account info - display page with new Model
	@RequestMapping(value= "/add/{student_id}")
	public String addTransportPage(@PathVariable("student_id") int id, Model model){
			model.addAttribute("student_id", id);
			Transport transport = new Transport();
			transport.setStudentId(id);
			model.addAttribute("transport", transport);
		
		return "transport/transport_add";
		
	}
	
	//For adding an transport
	@RequestMapping(value= "/addTransport/{student_id}", method = RequestMethod.POST)
	public String addAccount(@ModelAttribute("transport") Transport t, @PathVariable("student_id") int student_id, Model model){

			Student s = this.studentService.getStudentById(student_id);
			t.setStudent(s);
			this.transportService.addTransport(t);
			return "redirect:/Transport/students";
		
	}
	
	//For editing transport - display page with Model value
	@RequestMapping(value= "/edit/{transport_id}/{student_id}")
	public String editAccount(@PathVariable("transport_id") int id,@PathVariable("student_id") int student_id ,Model model){
		
			Transport t = this.transportService.getTransportById(id);
			t.setStudentId(student_id);
			model.addAttribute("transport", t);
			model.addAttribute("studentId",student_id);
		return "transport/transport_update";
		
	}
	
	//For updating a Transport
	@RequestMapping(value= "/updateTransport/{student_id}", method = RequestMethod.POST)
	public String updateAccount(@ModelAttribute("transport") Transport t, @PathVariable("student_id") int student_id, Model model){
			Student s = this.studentService.getStudentById(student_id);
			t.setStudent(s);
			this.transportService.updateTransport(t);
			return "redirect:/Transport/students";
		
	}
	
	//For removing a Transport
	@RequestMapping(value= "/remove/{transport_id}")
	public String removeStudent(@PathVariable("transport_id") int id){

		this.transportService.removeTransport(id);
		return "redirect:/Transport/students";
		
	}
}
