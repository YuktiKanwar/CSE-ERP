package spring.web.controller;

import java.security.Principal;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import spring.model.Department;
import spring.model.Lecture;
import spring.model.Student;
import spring.model.Subject;
import spring.model.TimeTable;
import spring.model.User;
import spring.model.UserRole;
import spring.service.StudentService;
import spring.service.SubjectService;
import spring.service.TimeTableService;
import spring.service.UserCRUDService;
import spring.service.DepartmentService;
import spring.service.LectureService;

@Controller
@RequestMapping(value = { "/HOD"}, method = RequestMethod.GET)
public class HODController{
	
	@Autowired
	@Qualifier("timeTableService")
	TimeTableService timeTableService;
	
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
	public String welcomePage() {
		return "hod/index";
	}
		
	//For adding a timetable
	@RequestMapping(value= "/addTimeTable", method = RequestMethod.POST)
	public String addTimeTable(@ModelAttribute("timetable")  TimeTable tt){

		Lecture l = lectureService.getLectureById(tt.getLectureId());
		tt.setLecture(l);
		int time = tt.getTime();
		List<TimeTable> timetables = this.timeTableService.getTimeTableByDayAndTime(tt.getDay(), time);
		if(timetables.size() > 0 ){
			return "redirect:/HOD/timetables/error";
		}
		else{
			this.timeTableService.addTimeTable(tt);
		}
	return "redirect:/HOD/timetables";
		
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
		
		//List Lectures
		@RequestMapping(value = "/lectures", method = RequestMethod.GET)
		public String listLectures(Model model, @RequestParam(value = "error",required = false) String error) {
			if(error != null)
			{
				model.addAttribute("error", "This lecture is being used in timetable.");
			}
			model.addAttribute("lecture", new Lecture());
			List<Lecture> listLectures = this.lectureService.listLectures();
			model.addAttribute("listLectures", listLectures);
			List<User> users = userService.listUsersByRoleFacultyAndHOD();
			Map<Integer,String> faculties = new HashMap<Integer,String>();
			for (int i = 0; i < users.size(); i++) {
				
				    	faculties.put(users.get(i).getId(), users.get(i).getUsername());
			
			}
			
			List<Subject> subjectList = subjectService.listSubjects();
			model.addAttribute("facultyList", faculties);
			model.addAttribute("subjectList", subjectList);
			return "hod/lecture";
		}
		
		//List TimeTables
		@RequestMapping(value = "/timetables", method = RequestMethod.GET)
		public String listTimeTables(Model model) {
			model.addAttribute("timetable", new TimeTable());
			List<TimeTable> listTimeTables = this.timeTableService.listTimeTables();
			model.addAttribute("listTimeTables", listTimeTables);
			List<Lecture> lectures = lectureService.listLectures();
			model.addAttribute("lectureList", lectures);
			return "hod/timetable";
		}
		
		//List TimeTables - error
		@RequestMapping(value = "/timetables/error", method = RequestMethod.GET)
		public String listTimeTablesError(Model model) {
			model.addAttribute("timetable", new TimeTable());
			List<TimeTable> listTimeTables = this.timeTableService.listTimeTables();
			model.addAttribute("listTimeTables", listTimeTables);
			List<Lecture> lectures = lectureService.listLectures();
			model.addAttribute("lectureList", lectures);
			model.addAttribute("error", "error");
			return "hod/timetable";
		}
		
		//For removing a timetable
		@RequestMapping(value= "/remove/timetable/{timetable_id}")
		public String removeTimeTable(@PathVariable("timetable_id") int id){
	
				this.timeTableService.removeTimeTable(id);
				return "redirect:/HOD/timetables";
			
		}
		
		//For removing a lecture
		@RequestMapping(value= "/remove/lecture/{lecture_id}")
		public String removeLecture(@PathVariable("lecture_id") int id){
	
			try{
				this.lectureService.removeLecture(id);
			}
			catch(org.springframework.dao.DataIntegrityViolationException e)
			{
				return "redirect:/HOD/lectures?error";
			}
		
		return "redirect:/HOD/lectures";
			
		}

		//For editing a lecture - display page with Model value
		@RequestMapping(value= "/edit/lecture/{lecture_id}")
		public String editLecture(@PathVariable("lecture_id") int id, Model model){
	
				Lecture l = this.lectureService.getLectureById(id);
				model.addAttribute("lecture", l);
				List<User> users = userService.listUsers();
				Map<Integer,String> faculties = new HashMap<Integer,String>();
				for (int i = 1; i < users.size(); i++) {
					
					    	faculties.put(users.get(i).getId(), users.get(i).getUsername());
				
				}
				
				List<Subject> subjectList = subjectService.listSubjects();
				model.addAttribute("facultyList", faculties);
				model.addAttribute("subjectList", subjectList);
			return "hod/lecture_update";
			
		}
		
		//For updating a lecture
		@RequestMapping(value= "/updateLecture", method = RequestMethod.POST)
		public String updateLecture(@ModelAttribute("lecture") Lecture l){
				
				Subject s = subjectService.getSubjectById(l.getSubjectId());
				l.setSubject(s);
				User u = userService.getUserById(l.getUserId());
				l.setUser(u);
				this.lectureService.updateLecture(l);
			
			return "redirect:/HOD/lectures";
			
		}
		
		//For editing a timetable - display page with Model value
		@RequestMapping(value= "/edit/timetable/{timetable_id}")
		public String editTimeTable(@PathVariable("timetable_id") int id, Model model){
	
				TimeTable tt = this.timeTableService.getTimeTableById(id);
				model.addAttribute("timetable", tt);
				List<Lecture> lectures = lectureService.listLectures();
				model.addAttribute("lectureList", lectures);
			
			return "hod/timetable_update";
			
		}
		
		//For updating a timetable
		@RequestMapping(value= "/updateTimeTable", method = RequestMethod.POST)
		public String updateTimeTable(@ModelAttribute("timetable") TimeTable tt){
				
				Lecture l = lectureService.getLectureById(tt.getLectureId());
				tt.setLecture(l);
				this.timeTableService.updateTimeTable(tt);
			
			return "redirect:/HOD/timetables";
			
		}
		
		
		
		
		
		//List Faculties
		@RequestMapping(value = "/faculty/list", method = RequestMethod.GET)
		public String listFaculties(Model model) {
			List<User> users = userService.listUsersByRoleFacultyAndHOD();
			model.addAttribute("facultyList", users);
			return "hod/listfaculty";
		}
		
		
		//List Attendance of Faculty
		@SuppressWarnings("null")
		@RequestMapping(value = "/faculty/{faculty_name}", method = RequestMethod.GET)
		public String listAttendanceFaculty(Model model,Principal user, @RequestParam(value = "error",required = false) String error, @PathVariable("faculty_name") String facultyName) {
			if(error != null)
			{
				model.addAttribute("error", "This lecture is being used in timetable.");
			}
			User u = this.userService.getUserByUserName(facultyName);
			List<Lecture> listLectures = this.lectureService.getLectureByFacultyId(u.getId());
			List<TimeTable> listTimeTables = new ArrayList<TimeTable>(); 
			listLectures.forEach((lecture)-> {
				List<TimeTable> timetables = this.timeTableService.getTimeTableByLectureId(lecture.getId());
				timetables.forEach((timetable)->{
					listTimeTables.add(timetable);
				});
			});
			model.addAttribute("listTimeTables", listTimeTables);
			model.addAttribute("facultyName", facultyName);
			return "hod/attendance_select";
		}
}
