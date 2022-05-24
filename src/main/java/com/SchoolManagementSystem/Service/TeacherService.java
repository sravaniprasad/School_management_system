package com.SchoolManagementSystem.Service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.SchoolManagementSystem.Entity.Studentlogindetails;
import com.SchoolManagementSystem.Entity.Studentmarks;
import com.SchoolManagementSystem.Entity.Teacher;

public interface TeacherService {

	public void addTeacher(Teacher teacher);
	
	public Teacher checkteacherLogin(String email, String password);
	
	
	List<Teacher> getAllTeachers();

	public void deleteTeacherByteacherId(int teacherId);
	
	public Teacher getTeacherByteacherId(int teacherId);

	public void updateTeacher(Teacher teacher1, int teacherId);

	public Teacher checkEmail(String teacherEmail);
	
	
}
