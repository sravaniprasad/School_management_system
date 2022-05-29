package com.SchoolManagementSystem.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Pageable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.SchoolManagementSystem.Entity.Admin;

import com.SchoolManagementSystem.Entity.Studentlogindetails;
import com.SchoolManagementSystem.Entity.Teacher;
import com.SchoolManagementSystem.Helper.Message;
import com.SchoolManagementSystem.Service.AdminService;
import com.SchoolManagementSystem.Service.StudentdetailsService;
import com.SchoolManagementSystem.Service.TeacherService;

@Controller
public class AdminController {
	@Autowired
	AdminService adminservice;
	
	
	@Autowired
	StudentdetailsService studentdetailsservice;
	

	@Autowired
	TeacherService teacherservice;
	
	@PostMapping("/checkadminLogin")
	public String checkadminLogin(@ModelAttribute Admin admin,HttpSession session,Model model) {
		
	Admin admin1=adminservice.checkadminLogin(admin.getName(),admin.getPassword());//studentservice.checkLogin(student.getName(), student.getPassword());
		if(admin1!=null) {
			session.setAttribute("admins", admin1);
			return "adminfolder/admindashboard";
		}
		else {
			return "adminfolder/adminlogin";
		}
		
	} 
	
	@GetMapping("/admindashboard")
	public String admindashboard(Model model) {
		
		model.addAttribute("admin", new Admin());
		return "adminfolder/admindashboard";
		
		
	}

	
	@GetMapping("/adminsignup")
	public String adminsignup(Model model) {
		
		model.addAttribute("admin", new Admin());
		return "adminfolder/adminsignup";
		
		
	}

	
	
	@GetMapping("/adminlogin")
	public String adminlogin(Model model) {
		 
		model.addAttribute("admin", new Admin());
		return "adminfolder/adminlogin";
		
	}
	
	@RequestMapping("/adminprofile")
	public String profile(Model model) {
		
	//	model.addAttribute("students", studentlogindetails);
		return "adminfolder/adminprofile";
}
	
	@GetMapping("/notification")
	public String notification(Model model) {
		 List<Admin>admin=this.adminservice.getAllAdmin();
		 model.addAttribute("adminObj", admin);
		model.addAttribute("admin", new Admin());
		return "adminfolder/notification";
		
	}
	@GetMapping("/editform/{adminId}")
	public String editForm(@PathVariable(value="adminId")int adminId,Model model) {
		Admin admin=adminservice.getAdminByadminId(adminId);
		model.addAttribute("admin",admin);
		model.addAttribute("admin", adminservice.getAdminByadminId(adminId));
		return "adminfolder/Editadmin";
	}
	
	@PostMapping("/adminupdate/{adminId}")
	public String updateAdmin(@ModelAttribute Admin admin  ,@PathVariable (value="adminId") int adminId) {
		
		//this.teacherservice.updateTeacher(teacher, teacherId);
		this.adminservice.addAdmin(admin);
		return "redirect:/notification";
		

	}
	

	@GetMapping("/addstudent")
	public String addstudent(Model model) {
		 
		model.addAttribute("title","Add Student");
		model.addAttribute("studentlogindetails", new Studentlogindetails());
		return "adminfolder/addstudent";
		
	}
	
	@PostMapping("/studentprocess")
	public String studentProcess(@ModelAttribute Studentlogindetails studentlogindetails,@ModelAttribute Admin admin,
			@RequestParam("profileimage") MultipartFile file,HttpSession session) throws IOException
	{
		//System.out.println("------------"+rollNo);
		if(file.isEmpty()) {
			System.out.println("file is empty");
			studentlogindetails.setImage("student.png");
			session.setAttribute("message0",new Message("student is not added !!","alert-danger"));
		}
		else {
			studentlogindetails.setImage(file.getOriginalFilename());
			File save=new ClassPathResource("static/img").getFile();
			Path path=Paths.get(save.getAbsolutePath()+File.separator+file.getOriginalFilename());
			Files.copy(file.getInputStream(), path,StandardCopyOption.REPLACE_EXISTING);
			System.out.println("file is uploaded");
		}
		//studentlogindetails.setRollNo(adminId);
		this.studentdetailsservice.addStudentlogindetails(studentlogindetails);
		//this.studentdetailsservice.addStudentlogindetails(studentlogindetails);
		System.out.println("added to database");
		session.setAttribute("message0",new Message("student added successfully!!","alert-success"));

		
		return "adminfolder/addstudent";
	}
	
	
	
	
	
	@GetMapping("/students/{rollNo}")
	 public String deleteStudent(@PathVariable(value="rollNo")int rollNo,Model model,HttpSession session) {
	
	studentdetailsservice.deleteStudentlogindetailsByrollNo(rollNo);
	session.setAttribute("message12",new Message("student deleted successfully","alert-success"));
	return "redirect:/viewstudents";
	 }
	 
//showupdateform-----updatebutton
@GetMapping("/updatestudentform/{rollNo}")
public String updatestudentForm(@PathVariable(value="rollNo")int rollNo,Model model) {
	Studentlogindetails studentlogindetails=studentdetailsservice.getStudentlogindetailsByrollNo(rollNo);
	model.addAttribute("studentlogindetails",studentlogindetails);
	model.addAttribute("studentlogindetails",studentdetailsservice.getStudentlogindetailsByrollNo(rollNo));
	return "adminfolder/updatestudentpage";
}

//saveupdatecontact---form
//"@{updateteacher{TeacherId}(TeacherId=${teacher.TeacherId})}"
@PostMapping("/studentupdate/{rollNo}")

public String updateStudent(@ModelAttribute Studentlogindetails studentlogindetails,@PathVariable (value="rollNo") int rollNo,
		@RequestParam("profileimage")MultipartFile file)throws Exception
{
	
	 
		if(file.isEmpty()) {
			System.out.println("file is empty");
			studentlogindetails.setImage("student.png");
			//session.setAttribute("message0",new Message("student is not added !!","alert-danger"));
		}
		else {
			studentlogindetails.setImage(file.getOriginalFilename());
			File save=new ClassPathResource("static/img").getFile();
			Path path=Paths.get(save.getAbsolutePath()+File.separator+file.getOriginalFilename());
			Files.copy(file.getInputStream(), path,StandardCopyOption.REPLACE_EXISTING);
			System.out.println("file is uploaded");
		}
	//this.teacherservice.updateTeacher(teacher, teacherId);
	
	this.studentdetailsservice.addStudentlogindetails(studentlogindetails);
	return "redirect:/viewstudents/0";
	  
	
}
	



@GetMapping("/viewteachers")
public String viewstudents(Model model) {
	
	List<Teacher> teacher = this.teacherservice.getAllTeachers() ;
	 model.addAttribute("teacherObj", teacher);
	
	 return "adminfolder/viewteachers";
}


//@GetMapping("/viewteachers/{page}")
//public String viewstudents(Model model,@PathVariable(value="page") Integer page) {
//	Pageable pageable= PageRequest.of(page, 2);
//	Page<Teacher> teacher = this.teacherservice.getAllTeachers(pageable) ;
//	 model.addAttribute("teacherObj", teacher);
//	 model.addAttribute("teacherObj", teacher);
//		model.addAttribute("currentPage", page);
//		model.addAttribute("totalPages", teacher.getTotalPages());
//	 return "adminfolder/viewteachers";
//}






	@GetMapping("/addteacher")
	public String addteacher(Model model) {
		 
		model.addAttribute("title","Add Teacher");
		model.addAttribute("teacher", new Teacher());
		return "adminfolder/addteacher";
		
	}
	
	
	@PostMapping("/teacherprocess")
	public String teacherProcess(@Valid @ModelAttribute Teacher teacher,Model model,BindingResult result,@ModelAttribute Admin admin,@RequestParam(value="agreement",defaultValue="false")boolean agreement,
			@RequestParam("profileimage") MultipartFile file,HttpSession session) throws IOException
	{
		
		if(file.isEmpty()) {
			System.out.println("file is empty");
			teacher.setImage("student.png");
			session.setAttribute("message01",new Message("teacher is not added !!","alert-danger"));
		}
		
		else {
			teacher.setImage(file.getOriginalFilename());
			File save=new ClassPathResource("static/img").getFile();
			Path path=Paths.get(save.getAbsolutePath()+File.separator+file.getOriginalFilename());
			Files.copy(file.getInputStream(), path,StandardCopyOption.REPLACE_EXISTING);
			System.out.println("file is uploaded");
		}
		
		this.teacherservice.addTeacher(teacher);
		System.out.println("added to database");
		session.setAttribute("message01",new Message("teacher added successfully!!","alert-success"));

		
		return "adminfolder/addteacher";
	}
	

@GetMapping("/viewteachers/{teacherId}")
	 public String deleteTeacher(@PathVariable(value="teacherId")int teacherId,Model model,HttpSession session) {
	
	teacherservice.deleteTeacherByteacherId(teacherId);
	session.setAttribute("message02",new Message("teacher deleted successfully","alert-success"));
	return "redirect:/adminfolder/viewteachers";
	 }
	 
//showupdateform-----updatebutton
@GetMapping("/updateform/{teacherId}")
public String updateForm(@PathVariable(value="teacherId")int teacherId,Model model) {
	Teacher teacher=teacherservice.getTeacherByteacherId(teacherId);
	model.addAttribute("teacher",teacher);
	model.addAttribute("teacher", teacherservice.getTeacherByteacherId(teacherId));
	return "adminfolder/updatepage";
}
//saveupdatecontact---form
//"@{updateteacher{TeacherId}(TeacherId=${teacher.TeacherId})}"
@PostMapping("/teacherupdate/{teacherId}")

public String updateTeacher(@ModelAttribute Teacher teacher,@PathVariable (value="teacherId") int teacherId,
		@RequestParam("profileimage")MultipartFile file)throws Exception {
	if(file.isEmpty()) {
		System.out.println("file is empty");
		teacher.setImage("student.png");
		//session.setAttribute("message0",new Message("student is not added !!","alert-danger"));
	}
	else {
		teacher.setImage(file.getOriginalFilename());
		File save=new ClassPathResource("static/img").getFile();
		Path path=Paths.get(save.getAbsolutePath()+File.separator+file.getOriginalFilename());
		Files.copy(file.getInputStream(), path,StandardCopyOption.REPLACE_EXISTING);
		System.out.println("file is uploaded");
	}
	//this.teacherservice.updateTeacher(teacher, teacherId);
	this.teacherservice.addTeacher(teacher);
	return "redirect:/viewteachers";
	

}


	 
	 
	@PostMapping(value="/do_admin")//,method=RequestMethod.POST)
	public String register(@Valid @ModelAttribute("admin") Admin admin,BindingResult result1,
			@RequestParam(value = "agreement",defaultValue ="false")
	boolean agreement,Model model,HttpSession session) {
		try {
			
	
			
			if(result1.hasErrors()) {
				System.out.println("ERROR"+result1.toString());
				model.addAttribute("admin",admin);
				return "adminfolder/adminsignup";
			}
			
			if(agreement) {
				session.setAttribute("message4",new Message("successfully registered!!","alert-success"));
				this.adminservice.addAdmin(admin);
				return "adminfolder/adminlogin";
			}
			else {
				session.setAttribute("message4",new Message("user not registered!!","alert-danger"));
				throw new Exception("you have not agreed the terms and conditions");
			}
			
				}
		catch(Exception e) 
		{
			System.out.println(e);
		}
			
		return "adminfolder/adminsignup";
	}
	
	
}
