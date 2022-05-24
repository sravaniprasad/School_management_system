package com.SchoolManagementSystem.ServiceImplementation;

import java.io.PrintWriter;
import java.util.List;

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
	@Override
	public List<Studentmarks> getAllStudentmarks() {
		// TODO Auto-generated method stub
		return marksrepo.findAll();
	}
	@Override
	public void downloadCSV(PrintWriter printwriter, List<Studentmarks> studentMarks) {
		// TODO Auto-generated method stub
		printwriter.write("enrollmentNo,mathematics,Html,java,python \n");
		for(Studentmarks marks:studentMarks) {
			
			printwriter.write(marks.getEnrollmentNo()+","+marks.getMathematics()+","+marks.getHtml()+","+marks.getPython()+","+marks.getJava()+","+marks.getTotal()+","+marks.getStatus()  +"\n");
			
		}
	}
	@Override
	public Studentmarks getStudentbyId(int studentRoll) {
		// TODO Auto-generated method stub
		return this.marksrepo.findById(studentRoll).get();
	}

	

}
