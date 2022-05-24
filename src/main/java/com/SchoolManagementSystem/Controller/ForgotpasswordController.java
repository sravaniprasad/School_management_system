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
import com.SchoolManagementSystem.Entity.Teacher;
import com.SchoolManagementSystem.Helper.Message;
import com.SchoolManagementSystem.Repository.StudentdetailsRepository;
import com.SchoolManagementSystem.Repository.TeacherRepository;
import com.SchoolManagementSystem.Service.StudentdetailsService;
import com.SchoolManagementSystem.Service.TeacherService;

import net.bytebuddy.utility.RandomString;

@Controller
public class ForgotpasswordController {
	Random random=new Random(10000);
	
	
	@Autowired
	private EmailService emailservice;
	
	@Autowired
	private StudentdetailsRepository studRepo;
	
	@Autowired
	private StudentdetailsService studentService;
	
	
	@Autowired
	private TeacherRepository teacherRepo;
	
	@Autowired
	private TeacherService teacherService;
	
	
	
	@RequestMapping("/forgot")
	public String OpenEmailform() {
		return "forgotemailform";
	}
	
	@RequestMapping("/teacherforgot")
	public String OpenTeacherEmailform() {
		return "teacherfolder/teacheremailform";
	}
	
	
	@PostMapping("/sendOTP")
	public String sendOTP(@RequestParam("studentEmail")String studentEmail,HttpSession session, Studentlogindetails student) {
		
		System.out.println("Email:"+studentEmail);
		
		//int otp=random.nextInt(99999);
		String otp=RandomString.make(7);
		System.out.println("otp:"+otp);
		
		String subject="Message from SMS:Request to Reset Student Password:";
		String message="Hello,  Here's your One Time Password (OTP)"
     			+ "OTP="+otp;
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

	
	
	
	@PostMapping("/sendteacherOTP")
	public String sendTeacherOTP(@RequestParam("teacherEmail")String teacherEmail,HttpSession session,@ModelAttribute Teacher teacher) {
		
		System.out.println("teacherEmail:"+teacherEmail);
		
		int otp=random.nextInt(99999);
		//String otp=RandomString.make(9);
		System.out.println("otp:"+otp);
		
		String subject="Message from SMS:Request to Reset Teacher Password:";
     	String message="Hello, Here's your One Time Password (OTP) "
     			+ "OTP="+otp;
     	String from="sravaniprasad939@gmail.com";
     	String to=teacherEmail;
     	 
		boolean flag=this.emailservice.sendEmail(subject, message, to,from);
		if(flag) {
			session.setAttribute("newotp", otp);
			session.setAttribute("teacherEmail", teacherEmail);
			return "teacherfolder/verifyteacher_otp";
		}
		else {
			session.setAttribute("message18",new Message("Check your email","alert-warning"));
			return "teacherfolder/teacheremailform";
		}
		//return "verifyOTP";
	}

	
	@PostMapping("/verify_otp")
	public String verifyOtp(@RequestParam("otp")String otp,HttpSession session) {
		
		String newotp=(String) session.getAttribute("newotp");
		String studentEmail=(String) session.getAttribute("studentEmail");
		if(newotp.equals(otp)) {
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
	
	@PostMapping("/verifyteacher_otp")
	public String verifyteacherOtp(@RequestParam("otp") int otp,HttpSession session) {
		
		int newotp=(int) session.getAttribute("newotp");
		String teacherEmail=(String) session.getAttribute("teacherEmail");
		if(newotp==otp) {
			Teacher teacher=this.teacherService.checkEmail(teacherEmail);
			if(teacher==null) {
				session.setAttribute("message001", new Message("Email does not exist as teacher","alert-primary"));
				return "teacherfolder/teacheremailform";
			}
			else {
				return "teacherfolder/updateteacherpassword";
			}
		}
		else {
			session.setAttribute("message033", new Message("InValid OTP","alert-danger"));
			return "teacherfolder/verifyteacher_otp";
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

@PostMapping("/updateteacherpassword")
public String updateteacherpass(@RequestParam("teacherPassword")String teacherPassword,@ModelAttribute Teacher teacher,HttpSession session)
{
	String teacherEmail=(String) session.getAttribute("teacherEmail");
	Teacher teacher2=this.teacherService.checkEmail(teacherEmail);
	teacher2.setTeacherPassword(teacher.getTeacherPassword());
	
	this.teacherRepo.save(teacher2);
	session.setAttribute("message002", new Message("Password Changed Successfully","alert-success"));
	
	return "teacherfolder/teacherlogin";
}
}
