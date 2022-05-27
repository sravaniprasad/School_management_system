package com.SchoolManagementSystem.ServiceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.SchoolManagementSystem.Entity.Teacher;
import com.SchoolManagementSystem.Repository.TeacherRepository;
import com.SchoolManagementSystem.Service.TeacherService;

@Service
public class TeacherServiceImpl  implements TeacherService{

	@Autowired
	TeacherRepository teacherRepository;

	@Override
	public void addTeacher(Teacher teacher) 
	{
		// TODO Auto-generated method stub
		this.teacherRepository.save(teacher);
	}

	@Override
	public Teacher checkteacherLogin(String email, String password)
	{
		// TODO Auto-generated method stub
		return this.teacherRepository.findByTeacherEmailAndTeacherPassword(email, password);
	}

	

	
	  @Override 
	  public List<Teacher> getAllTeachers() { 
		  return teacherRepository.findAll(); 
		  }
	 

	
	@Override
	public void deleteTeacherByteacherId(int teacherId) 
	{
		
		Teacher teacher=this.teacherRepository.findById(teacherId).get();
		this.teacherRepository.delete(teacher);
	}


	@Override
	public Teacher getTeacherByteacherId(int teacherId) 
	{
		// TODO Auto-generated method stub
		return teacherRepository.findById(teacherId).get();
	
	}

	
	@Override
	public void updateTeacher(Teacher teacher,int teacherId) {
		Teacher teacher1=teacherRepository.findById(teacherId).get();
		teacher1.setTeacherName(teacher1.getTeacherName());
		teacher1.setTeacherRole(teacher1.getTeacherRole());
		teacher1.setTeacherAddress(teacher1.getTeacherAddress());
		teacher1.setTeacherContact(teacher1.getTeacherContact());
		teacher1.setTeacherAlternateContact(teacher1.getTeacherAlternateContact());
		teacher1.setTeacherEmail(teacher1.getTeacherEmail());
		teacher1.setImage(teacher1.getImage());
		teacher1.setTeacherPassword(teacher1.getTeacherPassword());
		
		this.teacherRepository.save(teacher1);
	}

	@Override
	public Teacher checkEmail(String teacherEmail) {
		// TODO Auto-generated method stub
		return this.teacherRepository.findByteacherEmail(teacherEmail);
	}

	

	

	@Override
	public Page<Teacher> getAllTeachers(Pageable pageable) {
		// TODO Auto-generated method stub
		return teacherRepository.findAll(pageable);
	}


	

	
}


