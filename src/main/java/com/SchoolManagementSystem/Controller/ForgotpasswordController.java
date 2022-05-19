package com.SchoolManagementSystem.Controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.SchoolManagementSystem.Emailservice.EmailService;
import com.SchoolManagementSystem.Entity.Studentlogindetails;
import com.SchoolManagementSystem.Helper.Message;
import com.SchoolManagementSystem.Repository.StudentdetailsRepository;
import com.SchoolManagementSystem.Service.StudentdetailsService;

@Controller
public class ForgotpasswordController {
	Random random=new Random(10000);
	
	
	@Autowired
	private EmailService emailservice;
	
	@Autowired
	private StudentdetailsRepository studRepo;
	
	
	@Autowired
	private StudentdetailsService studentService;
	@RequestMapping("/forgot")
	public String OpenEmailform() {
		return "forgotemailform";
	}
	
	@PostMapping("/sendOTP")
	public String sendOTP(@RequestParam("studentEmail")String studentEmail,HttpSession session) {
		
		System.out.println("Email:"+studentEmail);
		
		int otp=random.nextInt(99999);
		System.out.println("otp:"+otp);
		
		String subject="Message from SMS:Request to Reset Password:";
     	String message="OTP="+otp;
     	String from="sravaniprasad939@gmail.com";
     	String to=studentEmail;
     	
		boolean flag=this.emailservice.sendEmail(subject, message, to,from);
		if(flag) {
			session.setAttribute("newotp", otp);
			session.setAttribute("studentEmail", studentEmail);
			return "verify_otp";
		}
		else {
			session.setAttribute("message8",new Message("Check your email","alert-warning"));
			return "forgotemailform";
		}
		//return "verifyOTP";
	}

	
	@PostMapping("/verify_otp")
	public String verifyOtp(@RequestParam("otp")int otp,HttpSession session) {
		
		int newotp=(int) session.getAttribute("newotp");
		String studentEmail=(String) session.getAttribute("studentEmail");
		if(newotp==otp) {
			Studentlogindetails stud=this.studentService.checkEmail(studentEmail);
			if(stud==null) {
				session.setAttribute("message01", new Message("student does not exist","alert-primary"));
				return "forgotemailform";
			}
			else {
				return "updatepassword";
			}
		}
		else {
			session.setAttribute("message33", new Message("InValid OTP","alert-danger"));
			return "verify_otp";
		}
		
	}
	
	
	@PostMapping("/updatepassword")
	public String update(@RequestParam("password")String password,@ModelAttribute Studentlogindetails student,HttpSession session)
	{
		String email=(String) session.getAttribute("studentEmail");
		Studentlogindetails stud1=this.studentService.checkEmail(email);
		stud1.setPassword(student.getPassword());
		this.studRepo.save(stud1);
		session.setAttribute("message02", new Message("Password Changed Successfully","alert-success"));
		
		return "studentfolder/studentlogin";
	}
}
