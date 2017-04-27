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

import spring.model.Account;
import spring.model.Book;
import spring.model.Department;
import spring.model.Student;
import spring.model.Subject;
import spring.service.AccountService;
import spring.service.StudentService;

@Controller
@RequestMapping(value = { "/Accounts"}, method = RequestMethod.GET)
public class AccountsController{
	@Autowired
	@Qualifier("studentService")
	StudentService studentService;
	
	@Autowired
	@Qualifier("accountService")
	AccountService accountService;
	
	@RequestMapping(value = { "/index"}, method = RequestMethod.GET)
	public ModelAndView welcomePage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("accounts/index");
		return model;
	}
	
	//Issue Account - List Students
	@RequestMapping(value= "/students")
	public String issueAccount1(Model model){
			List<Student> studentList = this.studentService.listStudents();
			model.addAttribute("listStudents", studentList);
		return "accounts/students";
		
	}
	
	//For displaying fee detail of a student
	@RequestMapping(value= "/view/{student_id}")
	public String studentFeeDetail(@PathVariable("student_id") int student_id, Model model){

			Account a = this.accountService.getAccountByStudentId(student_id);
			model.addAttribute("studentId",student_id);
			model.addAttribute("account", a);
			
		return "accounts/view_fee";
		
	}
	
	//For editing Account - display page with Model value
	@RequestMapping(value= "/edit/{account_id}/{student_id}")
	public String editAccount(@PathVariable("account_id") int id,@PathVariable("student_id") int student_id ,Model model){
		
			Account a = this.accountService.getAccountById(id);
			a.setStudentId(student_id);
			model.addAttribute("account", a);
			model.addAttribute("studentId",student_id);
		return "accounts/account_update";
		
	}
	
	//For adding Account info - display page with new Model
	@RequestMapping(value= "/add/{student_id}")
	public String addAccountPage(@PathVariable("student_id") int id, Model model){
			model.addAttribute("student_id", id);
			Account account = new Account();
			account.setStudentId(id);
			model.addAttribute("account", account);
		
		return "accounts/account_add";
		
	}
	//For adding an Account
	@RequestMapping(value= "/addAccount/{student_id}", method = RequestMethod.POST)
	public String addAccount(@ModelAttribute("account") Account a, @PathVariable("student_id") int student_id, Model model){

			Student s = this.studentService.getStudentById(student_id);
			a.setStudent(s);
			this.accountService.addAccount(a);
			return "redirect:/Accounts/students";
		
	}
	
	//For updating an Account
	@RequestMapping(value= "/updateAccount/{student_id}", method = RequestMethod.POST)
	public String updateAccount(@ModelAttribute("account") Account a, @PathVariable("student_id") int student_id, Model model){
			Student s = this.studentService.getStudentById(student_id);
			int total_fee = a.getTotalFee();
			a.setStudent(s);
			this.accountService.updateAccount(a);
			return "redirect:/Accounts/students";
		
	}
}
