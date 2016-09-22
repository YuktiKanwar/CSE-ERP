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

import spring.model.User;
import spring.model.UserRole;
import spring.service.UserCRUDService;
import spring.service.UserRoleService;

@Controller
@RequestMapping(value = { "/User"}, method = RequestMethod.GET)
public class UserController{

	@Autowired
	@Qualifier("userCRUDService")
	UserCRUDService userCRUDService;
	
	@Autowired
	@Qualifier("userRoleService")
	UserRoleService userRoleService;
	
	@RequestMapping(value = { "/index"}, method = RequestMethod.GET)
	public ModelAndView welcomePage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("superuser/index");
		return model;
	}

	//List Users
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String listUsers(Model model) {
		model.addAttribute("user", new User());
		List<User> listUsers = this.userCRUDService.listUsers();
		// Get the roles
		HashMap<String,Set<UserRole>> userRolesSet = new HashMap<String,Set<UserRole>>();
		Set<UserRole> userRoles = new HashSet<UserRole>();
		for (int i = 0; i < listUsers.size(); i++) {
			User listUser = listUsers.get(i);
			userRoles = listUser.getUserRole();
			for(UserRole u: userRoles) {
			    UserRole waiu = u;
			    break;
			}
			userRolesSet.put(String.valueOf(i), userRoles);
			
		}
		model.addAttribute("listUsers", listUsers);
		model.addAttribute("userRoles",userRolesSet);
		return "superuser/user";
	}
	
	//For adding a user
	@RequestMapping(value= "/add", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") User u){

			this.userCRUDService.addUser(u);
			List<String> userRoles = u.getUserRoleString();
			for(String userRole : userRoles)
			{
				UserRole ur = new UserRole(u,userRole);	
				this.userRoleService.addUserRole(ur);
			}
		
		return "redirect:/User/users";
		
	}
	
	// For updating a user
	@RequestMapping(value= "/update", method = RequestMethod.POST)
	public String updateUser(@ModelAttribute("user") User u){

		this.userCRUDService.updateUser(u);

	
	return "redirect:/User/users";
	
	}
	
	//For removing a user
	@RequestMapping(value= "/remove/{username}")
	public String removeUser(@PathVariable("username") String username){
		
		
			this.userCRUDService.removeUser(username);
		
		return "redirect:/User/users";
		
	}
	
	//For editing a user
    @RequestMapping("/edit/{username}")
    public String editUser(@PathVariable("username") String username, Model model){
        model.addAttribute("user", this.userCRUDService.getUserByUserName(username));
		List<User> listUsers = this.userCRUDService.listUsers();
		// Get the roles
		HashMap<String,Set<UserRole>> userRolesSet = new HashMap<String,Set<UserRole>>();
		Set<UserRole> userRoles = new HashSet<UserRole>();
		for (int i = 0; i < listUsers.size(); i++) {
			User listUser = listUsers.get(i);
			userRoles = listUser.getUserRole();
			userRolesSet.put(String.valueOf(i), userRoles);
			
		}
		model.addAttribute("listUsers", listUsers);
		model.addAttribute("userRoles",userRolesSet);
        return "superuser/user_update";
    }
	
	@ModelAttribute("rolesList") 
	private Map populateDefaultModel() {
		 

		Map<String,String> roles = new HashMap<String,String>();
		roles.put("ROLE_ACCOUNTS", "ROLE_ACCOUNTS");
		roles.put("ROLE_ADMISSION", "ROLE_ADMISSION");
		roles.put("ROLE_FACULTY", "ROLE_FACULTY");
		roles.put("ROLE_HOD", "ROLE_HOD");
		roles.put("ROLE_LIBRARY", "ROLE_LIBRARY");
		roles.put("ROLE_T&P", "ROLE_T&P");
		roles.put("ROLE_TRANSPORT", "ROLE_TRANSPORT");
		return roles;

	}
}
