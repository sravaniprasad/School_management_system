package com.SchoolManagementSystem.ServiceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SchoolManagementSystem.Entity.Studentmarks;
import com.SchoolManagementSystem.Repository.MarksRepository;
import com.SchoolManagementSystem.Service.MarksService;

@Service
public class MarksServiceImpl implements MarksService {

	
	@Autowired
	MarksRepository marksrepo;
	@Override
	public void addStudentmarks(Studentmarks studentMarks) {
		// TODO Auto-generated method stub
		this.marksrepo.save(studentMarks);
		
	}

}
