package spring.web.controller;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import spring.model.Attendance;
import spring.model.Department;
import spring.model.Lecture;
import spring.model.Student;
import spring.model.Subject;
import spring.model.TimeTable;
import spring.model.User;
import spring.service.AttendanceService;
import spring.service.LectureService;
import spring.service.StudentService;
import spring.service.TimeTableService;
import spring.service.UserCRUDService;

@Controller
@RequestMapping(value = { "/Faculty"}, method = RequestMethod.GET)
@ComponentScan("spring.service")
public class FacultyController{
	
	@Autowired
	@Qualifier("lectureService")
	LectureService lectureService;
	
	@Autowired
	@Qualifier("userCRUDService")
	UserCRUDService userService;
	
	@Autowired
	@Qualifier("timeTableService")
	TimeTableService timeTableService;
	
	@Autowired
	@Qualifier("studentService")
	StudentService studentService;
	
	@Autowired
	@Qualifier("attendanceService")
	AttendanceService attendanceService;
	
	@RequestMapping(value = { "/index"}, method = RequestMethod.GET)
	public ModelAndView welcomePage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("faculty/index");
		return model;
	}
	
	//List Lectures
	@RequestMapping(value = "/lectures", method = RequestMethod.GET)
	public String listLectures(Model model,Principal user, @RequestParam(value = "error",required = false) String error) {
		if(error != null)
		{
			model.addAttribute("error", "This lecture is being used in timetable.");
		}
		String facultyName = user.getName();
		User u = this.userService.getUserByUserName(facultyName);
		List<Lecture> listLectures = this.lectureService.getLectureByFacultyId(u.getId());
		model.addAttribute("listLectures", listLectures);
		return "faculty/lecture";
	}
	
	//List TimeTable
	@SuppressWarnings("null")
	@RequestMapping(value = "/timetable", method = RequestMethod.GET)
	public String listTimeTable(Model model,Principal user, @RequestParam(value = "error",required = false) String error) {
		if(error != null)
		{
			model.addAttribute("error", "This lecture is being used in timetable.");
		}
		String facultyName = user.getName();
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
		return "faculty/timetable";
	}
	
	//List Attendance
	@SuppressWarnings("null")
	@RequestMapping(value = "/attendance", method = RequestMethod.GET)
	public String listAttendance(Model model,Principal user, @RequestParam(value = "error",required = false) String error) {
		if(error != null)
		{
			model.addAttribute("error", "This lecture is being used in timetable.");
		}
		String facultyName = user.getName();
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
		return "faculty/attendance_select";
	}
	//Mark Attendance
	@SuppressWarnings("null")
	@RequestMapping(value = "/attendance/mark/{timetable_id}/{date}")
	public String markAttendance(Model model,Principal user, @PathVariable("timetable_id") int timetable_id, @PathVariable("date") String date) {
		String facultyName = user.getName();
		
		String DateString = date.replace('-', '/');
		String[] dateStrings = DateString.split("/");
		String DateStringForDAO = dateStrings[2] + '/' + dateStrings[1] + '/' + dateStrings[0];
		TimeTable t = this.timeTableService.getTimeTableById(timetable_id);
		Lecture l = t.getLecture();
		Subject s = l.getSubject();
		Department d =s.getDepartment();
		List<Student> students = this.studentService.getStudentByDepartmentId(d.getId());
		List<Student> finalStudents = new ArrayList<Student>();
		students.forEach((student) ->{
			if(student.getSemester() == s.getSemester()){
				finalStudents.add(student);
			}
		});
		List<Attendance> attendance = this.attendanceService.listAttendancesByDateAndTimeTable(DateStringForDAO, timetable_id);
		model.addAttribute("listStudents", finalStudents);
		model.addAttribute("listAttendance", attendance);
		model.addAttribute("timetable_id", timetable_id);
		model.addAttribute("facultyName", facultyName);
		model.addAttribute("date", date);
		return "faculty/attendance_mark";
	}
	
	//Save Attendance
	@SuppressWarnings("null")
	@RequestMapping(value = "/attendance/save/", method = RequestMethod.POST)
	@ResponseBody
	public String saveAttendance(HttpServletRequest request){
		int timetable_id = Integer.parseInt(request.getParameter("timetable_id"));
		int student_id = Integer.parseInt(request.getParameter("student_id"));
		String value = request.getParameter("value");
		String DateString = request.getParameter("date");
		DateString = DateString.replace('-', '/');
		String[] dateStrings = DateString.split("/");
		String DateStringForDAO = dateStrings[2] + '/' + dateStrings[1] + '/' + dateStrings[0]; 
	    DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 
	    Date date = new Date();
        try {
			date = df.parse(DateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        Attendance existingAttendance = this.attendanceService.getAttendanceByDateAndTimeTableAndStudent(DateStringForDAO, timetable_id, student_id);
        if(existingAttendance != null){
        	existingAttendance.setValue(value);
        	this.attendanceService.addAttendance(existingAttendance);
        }
        else{
        	Attendance attendance = new Attendance(timetable_id,student_id,date,value);
        	this.attendanceService.addAttendance(attendance);
        }
		return String.valueOf(student_id);
	}
}
