package com.SchoolManagementSystem.Controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
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
import com.SchoolManagementSystem.Emailservice.EmailService;
import com.SchoolManagementSystem.Entity.Studentlogindetails;
import com.SchoolManagementSystem.Entity.Studentmarks;
import com.SchoolManagementSystem.Entity.Teacher;
import com.SchoolManagementSystem.Helper.Message;
import com.SchoolManagementSystem.Repository.StudentdetailsRepository;
import com.SchoolManagementSystem.Service.StudentdetailsService;
@Controller

public class StudentloginController {
	
	

	@Autowired
	StudentdetailsService studentdetailsservice;

	@Autowired
    StudentdetailsRepository studentrepository;

	
	@PostMapping("/checkstudentLogin")
	public String checkstudentLogin(Model model,@ModelAttribute Studentlogindetails studentlogindetails,HttpSession session) {
		
	Studentlogindetails student3=studentdetailsservice.checkstudentLogin(studentlogindetails.getStudentEmail(), studentlogindetails.getPassword());
		
	if(student3!=null) {
		session.setAttribute("students", student3);
			return "studentfolder/studentdashboard";
		}
		else {
			return "studentfolder/studentlogin";
		}
		
	} 
	 @GetMapping("/viewmarksheet{studentRoll}")
		public String student(Model model, @PathVariable int studentRoll) {
			
			model.addAttribute("studentMarks",new Studentmarks());
			
			return "studentfolder/viewmarksheet";
		}
	 
	 
	 
	 
	 @GetMapping("/dashboard")
		public String dashboard(Model model) {
			model.addAttribute("title","dashboard-school mgmy system");
			
			return "dashboard";
			
			
		}
		
		@GetMapping("/home")
		public String home(Model model) {
			model.addAttribute("title","home-school mgmy system");
			
			return "home";
			
			
		}
		
		@GetMapping("/about")
		public String about(Model model) {
			model.addAttribute("title","about-school mgmy system");
			
			return "about";
			
			
		}
	 
	 
	 
	 
	 @GetMapping("/viewstudents")
	 public String viewstudents(Model model) {
		List<Studentlogindetails> studentlistDetails = this.studentdetailsservice.getAllStudents() ;
		 model.addAttribute("studentObj", studentlistDetails);
		 
		 return "adminfolder/viewstudents";
	 }
	 
	
	 
	 
	 @RequestMapping("/students")
		public String students(Model model) {
		 List<Studentlogindetails> studentlistDetails = this.studentdetailsservice.getAllStudents() ;
			
			model.addAttribute("studentObj", studentlistDetails);
			
			return "teacherfolder/students";
	}
		

	 
		@PostMapping("/changepassword")
	 public String changePassword(@RequestParam("oldpassword")String oldpassword,@RequestParam("newpassword")String newpassword,HttpSession session) {
		 System.out.println("old password"+oldpassword);	
		 System.out.println("new password"+newpassword);
//		 String fullName=session.getId();
//		 Studentlogindetails login=this.studentdetailsservice.getStudentlogindetailsByrollNo(fullName);
//		System.out.println(login.getPassword());
		 return "studentdashboard";
	 }
		

		@GetMapping("/settings")
		public String settings() {
			return "studentfolder/settings";
		}
		
	 
	@RequestMapping("/studentlogin")
	public String studentLogin(Model model) {
		model.addAttribute("studentlogindetails", new Studentlogindetails());
		
		return "studentfolder/studentlogin";
	
	}
	
	
	@RequestMapping("/studentdashboard")
	public String studentdashboard(Model model) {
		model.addAttribute("studentlogindetails", new Studentlogindetails());
		
		return "studentfolder/studentdashboard";
	
	}
	
	@RequestMapping("/studentsignup")
	public String studentSignup(Model model) {
		
		model.addAttribute("studentlogindetails",new Studentlogindetails());
		
		return "studentfolder/studentsignup";
	}
	
	@RequestMapping("/studentdetails/{rollNo}")
	public String studentDetails(@PathVariable("rollNo")int rollNo,Model model) {
		Optional<Studentlogindetails> studentoptional=this.studentrepository.findById(rollNo);
		Studentlogindetails studentlogindetails=studentoptional.get();
		model.addAttribute("student", studentlogindetails);
		return "studentdetails";
	}



	
	@RequestMapping("/profile")
		public String profile(Model model) {
			
		//	model.addAttribute("students", studentlogindetails);
			return "studentfolder/studentprofile";
}
	
	
	@PostMapping("/saveimage{rollNo}")
	public String updateStudent(@ModelAttribute Studentlogindetails studentlogindetails,@PathVariable (value="rollNo") int rollNo,
			@RequestParam("profileimage")MultipartFile file)throws Exception
	{
		 Studentlogindetails studentprofile=this.studentdetailsservice.getStudentlogindetailsByrollNo(rollNo);
		 
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
		studentprofile.setImage(studentlogindetails.getImage());
		this.studentdetailsservice.addStudentlogindetails(studentlogindetails);
		return "redirect:/studentfolder/studentprofile";
		  
		
	}
		

	@PostMapping(value="/register")//,method=RequestMethod.POST)
	public String registerstudent(@Valid @ModelAttribute("studentlogindetails") Studentlogindetails studentlogindetails,BindingResult result,
			@RequestParam(value = "agreement",defaultValue ="false")
	boolean agreement,Model model,HttpSession session) {
		try {
			
	
			
			if(result.hasErrors()) {
				System.out.println("ERROR"+result.toString());
				model.addAttribute("studentlogindetails",studentlogindetails);
				return "studentfolder/studentsignup";
			}
			
			if(agreement) {
				session.setAttribute("message1",new Message("successfully registered!!","alert-success"));
				this.studentdetailsservice.addStudentlogindetails(studentlogindetails);
				return "studentfolder/studentlogin";
			}
			else {
				session.setAttribute("message1",new Message("user not registered!!","alert-danger"));
				throw new Exception("you have not agreed the terms and conditions");
			}
			
				}
		catch(Exception e) 
		{
			System.out.println(e);
		}
			
		return "studentfolder/studentsignup";
	}
	
	
	
}
