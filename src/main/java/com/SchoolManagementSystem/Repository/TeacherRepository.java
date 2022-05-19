package com.SchoolManagementSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.SchoolManagementSystem.Entity.Studentmarks;

import com.SchoolManagementSystem.Entity.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Integer> {

	
	public Teacher findByTeacherEmailAndTeacherPassword(String email, String password);


}