package com.SchoolManagementSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SchoolManagementSystem.Entity.Studentlogindetails;

public interface StudentdetailsRepository extends JpaRepository<Studentlogindetails,Integer>{
	public Studentlogindetails findBystudentEmailAndPassword(String studentEmail,String password);

	
	public Studentlogindetails getStudentlogindetailsByrollNo(int rollNo);
	 
	public Studentlogindetails findBystudentEmail(String studentEmail);
}
