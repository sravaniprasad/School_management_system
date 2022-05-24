package com.SchoolManagementSystem.Service;



import java.io.PrintWriter;
import java.util.List;


import com.SchoolManagementSystem.Entity.Studentmarks;

public interface MarksService {

	//public List<Studentmarks> getAllStudentsmarks();

	public void addStudentmarks(Studentmarks studentMarks);

	public List<Studentmarks> getAllStudentmarks();

	public void downloadCSV(PrintWriter printwriter,List<Studentmarks> studentMarks);
	
	public Studentmarks getStudentbyId(int studentRoll);

	//public void downloadPDF(PrintWriter writer, List<Studentmarks> studentMarks);

	
}
