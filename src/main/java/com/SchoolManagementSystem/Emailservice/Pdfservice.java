package com.SchoolManagementSystem.Emailservice;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SchoolManagementSystem.Entity.Studentmarks;
import com.SchoolManagementSystem.Repository.MarksRepository;

@Service
@Transactional
public class Pdfservice {

	@Autowired
	private MarksRepository marksRepo;
	
	public List <Studentmarks> listAll(){
		return marksRepo.findAll();
	}
}
