
	package com.SchoolManagementSystem.Controller;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import com.SchoolManagementSystem.Entity.Studentlogindetails;
import com.SchoolManagementSystem.Entity.Studentmarks;
import com.SchoolManagementSystem.Entity.Teacher;
import com.SchoolManagementSystem.Helper.Message;
import com.SchoolManagementSystem.Service.StudentdetailsService;
import com.SchoolManagementSystem.Service.TeacherService;

	@Controller
	public class TeacherController {
		@Autowired
		TeacherService teacherService;
		
		@Autowired
		StudentdetailsService studentdetailsservice;
		
		@PostMapping("/checkteacherLogin")
		public String checkteacherLogin(@ModelAttribute Teacher teacher,HttpSession session) {
			
		Teacher teacher1=teacherService.checkteacherLogin(teacher.getTeacherEmail(), teacher.getTeacherPassword());
			if(teacher1!=null) {
				session.setAttribute("teacher", teacher1);
				return "teacherfolder/teacherdashboard";
			}
			else {
				return "teacherfolder/teacherlogin";
			}
			
		} 
		
		@RequestMapping("/profile1")
		public String profile(Model model) {
			
		//	model.addAttribute("students", studentlogindetails);
			return "teacherfolder/teacherprofile";
}
		@RequestMapping("/teacherdashboard")
		public String teacherdashboard(Model model) {
			model.addAttribute("teacher", new Teacher());
			
			return "teacherfolder/teacherdashboard";
		
		}
		
		
		@RequestMapping("/teacherlogin")
		public String teacherlogin(Model model) {
			model.addAttribute("teacher", new Teacher());
			
			return "teacherfolder/teacherlogin";
		
		}
		
		
		@RequestMapping("/teachersignup")
		public String teachersignup(Model model) {
			
			model.addAttribute("teacher", new Teacher());
			
			return "teacherfolder/teachersignup";
	}

		
//		@GetMapping("/page/{pageNumber}")
//		public String viewstudentsPage(Model model,@PathVariable(value="pageNumber")int pageNumber) {
//			Pageable pageable= PageRequest.of(pageNumber, 2);
//			Page<Teacher> teacherdetails=this.teacherService.getAllTeachers(pageable);
//			//List<Teacher> teacher1 = this.teacherservice.getAllTeachers() ;
//			 model.addAttribute("teacherObj", teacherdetails);
//			model.addAttribute("currentPage", pageNumber);
//			model.addAttribute("totalPages", teacherdetails.getTotalPages());
//			 return "adminfolder/viewteachers";
//		}
		 
		 
		
		@PostMapping(value="/teacherregister")//,method=RequestMethod.POST)
		public String registerteacher(@Valid @ModelAttribute("teacher") Teacher teacher,BindingResult result,
				@RequestParam(value = "agreement",defaultValue ="false")
		boolean agreement,Model model,HttpSession session) {
			try {
				
		
				
				if(result.hasErrors()) {
					System.out.println("ERROR"+result.toString());
					model.addAttribute("teacher",teacher);
					return "teacherfolder/teachersignup";
				}
				
				if(agreement) {
					session.setAttribute("message2",new Message("successfully registered!!","alert-success"));
					this.teacherService.addTeacher(teacher);
					return "teacherfolder/teacherlogin";
				}
				else {
					session.setAttribute("message2",new Message("user not registered!!","alert-danger"));
					throw new Exception("you have not agreed the terms and conditions");
				}
				
					}
			catch(Exception e) 
			{
				System.out.println(e);
			}
				
			return "teacherfolder/teachersignup";
		}
		
		
		
		
	}

