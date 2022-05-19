package com.SchoolManagementSystem.Service;

import java.util.List;

import javax.validation.Valid;
import com.SchoolManagementSystem.Entity.Studentlogindetails;
import com.SchoolManagementSystem.Entity.Studentmarks;

public interface StudentdetailsService {

	public void addStudentlogindetails(Studentlogindetails studentlogindetails);
	
public Studentlogindetails checkstudentLogin(String studentEmail,String password);
	

	public List<Studentlogindetails> getAllStudents();

	public void deleteStudentlogindetailsByrollNo(int rollNo);

	public Studentlogindetails getStudentlogindetailsByrollNo(int rollNo);

	void updateStudent(Studentlogindetails studentlogindetails, int rollNo);

	public Studentlogindetails checkEmail(String studentEmail);
	
	



}
