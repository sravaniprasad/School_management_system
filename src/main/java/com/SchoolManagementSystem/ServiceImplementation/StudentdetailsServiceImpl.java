package com.SchoolManagementSystem.ServiceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.SchoolManagementSystem.Entity.Studentlogindetails;
import com.SchoolManagementSystem.Entity.Teacher;
import com.SchoolManagementSystem.Repository.StudentdetailsRepository;
import com.SchoolManagementSystem.Service.StudentdetailsService;

@Service
public class StudentdetailsServiceImpl implements StudentdetailsService{

	
	@Autowired
	StudentdetailsRepository studentRepository;
	
	@Override
	public void addStudentlogindetails(Studentlogindetails studentlogindetails) {
		this.studentRepository.save(studentlogindetails);
		
	}
	
	
	@Override
	public Studentlogindetails checkstudentLogin(String studentEmail, String password) {
		// TODO Auto-generated method stub
		return this.studentRepository.findBystudentEmailAndPassword(studentEmail, password);
	}
@Override
public List<Studentlogindetails> getAllStudents() {
	// TODO Auto-generated method stub
	
	
	return studentRepository.findAll();
}


@Override
public void deleteStudentlogindetailsByrollNo(int rollNo) {
	Studentlogindetails studentlogindetails=this.studentRepository.findById(rollNo).get();
	this.studentRepository.delete(studentlogindetails);
	
}


@Override
public Studentlogindetails getStudentlogindetailsByrollNo(int rollNo) {
	// TODO Auto-generated method stub
	return studentRepository.findById(rollNo).get();
}
	
@Override
public void updateStudent(Studentlogindetails studentlogindetails1,int rollNo) {
	Studentlogindetails studentlogindetails=studentRepository.findById(rollNo).get();
	studentlogindetails.setFullName(studentlogindetails.getFullName());
	studentlogindetails.setFatherName(studentlogindetails.getFatherName());
	studentlogindetails.setMotherName(studentlogindetails.getMotherName());
	studentlogindetails.setPercentage(studentlogindetails.getPercentage());
	studentlogindetails.setStudentAddress(studentlogindetails.getStudentAddress());
	studentlogindetails.setStudentEmail(studentlogindetails.getStudentEmail());
	studentlogindetails.setImage(studentlogindetails.getImage());
	studentlogindetails.setPassword(studentlogindetails.getPassword());
	
	this.studentRepository.save(studentlogindetails);
}


@Override
public Studentlogindetails checkEmail(String studentEmail) {
	// TODO Auto-generated method stub
	return this.studentRepository.findBystudentEmail(studentEmail);
}


@Override
public Page<Studentlogindetails> getAllStudents(Pageable pageable) {
	// TODO Auto-generated method stub
	return studentRepository.findAll(pageable);
}




}

