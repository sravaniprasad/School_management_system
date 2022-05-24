package com.SchoolManagementSystem.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.SchoolManagementSystem.Entity.Studentlogindetails;
import com.SchoolManagementSystem.Entity.Studentmarks;
import com.SchoolManagementSystem.Helper.Message;
import com.SchoolManagementSystem.Service.MarksService;
import com.SchoolManagementSystem.Service.StudentdetailsService;
import com.SchoolManagementSystem.Service.TeacherService;

@Controller

public class MarksController {

	@Autowired
	MarksService marksService;
	
	@Autowired
	StudentdetailsService studentdetailsservice;
	 
	 
	 @GetMapping("/marklist/{rollNo}")
		public String assignmarks(Model model,@PathVariable("rollNo") int rollNo, HttpSession session) 
	 {
			System.out.println("started............");
		     session.setAttribute("studentRoll", rollNo);
			Studentmarks studentmarks=  new Studentmarks();
		
			model.addAttribute("studentMarks",studentmarks);
			return "teacherfolder/assignmarks";
			
		}
	

	 
	 
	 @GetMapping("/marksheet/{studentRoll}")
		public String Studentmarks(Model model,HttpSession session,@PathVariable("studentRoll") int studentRoll) {
		
	//	 Studentmarks studmarks=marksService.
		 
		 
		Studentmarks studentmarks=	this.marksService.getStudentbyId(studentRoll);
			model.addAttribute("studentMarks",studentmarks);
		//	model.addAttribute("Marks", new Studentmarks());
			return "studentfolder/marksheet";
					
		}
	 
	 
	 @PostMapping(value="/result{studentRoll}")//,method=RequestMethod.POST)
		public String viewMarks(@Valid @ModelAttribute("studentMarks") Studentmarks studentMarks,BindingResult result1,
				@PathVariable("studentRoll") int studentRoll,
				@RequestParam(value = "agreement",defaultValue ="false")
		boolean agreement,Model model,HttpSession session) {
			try {
						System.out.println("Student roll     "+studentRoll);		
Studentlogindetails  studentlogindetails=this.studentdetailsservice.getStudentlogindetailsByrollNo(studentRoll);
				model.addAttribute("studentMarks",studentMarks);
				if(result1.hasErrors()) {
					System.out.println("ERROR"+result1.toString());
					
					return "teacherfolder/assignmarks";
				}
				
				if(agreement) {
					studentMarks.setStudent(studentlogindetails);
					studentlogindetails.getStudentmarks().add(studentMarks);
					
					session.setAttribute("message02",new Message("Successfully Assigned!!","alert-success"));
					this.marksService.addStudentmarks(studentMarks);
					this.studentdetailsservice.addStudentlogindetails(studentlogindetails);
					
					return "teacherfolder/result";
				}
				else {
					session.setAttribute("message02",new Message("Some thing is wrong! please, Accept Terms and Conditions!!","alert-danger"));
					throw new Exception("you have not agreed the terms and conditions");
				}
				
					}
			catch(Exception e) 
			{
				System.out.println(e);
			}
				
			return "teacherfolder/assignmarks";
		} 
		 

	
	
}
