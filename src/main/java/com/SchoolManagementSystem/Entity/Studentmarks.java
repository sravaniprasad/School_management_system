package com.SchoolManagementSystem.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Studentmarks")
public class Studentmarks {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int enrollmentNo;
	
	@NotNull(message = " please provide marks!!!")
	private Integer mathematics;
	
	@NotNull(message = " please provide marks!!!")
	private Integer java;
	
	@NotNull(message = " please provide marks!!!")
	private Integer python;
	
	@NotNull(message = " please provide marks!!!")
	private Integer html;
	
	@ManyToOne
	private Studentlogindetails student;
	
	
//	private Integer total;
//	
//	private Integer average;
//	
//	private String status;
//	
	public Integer getTotal() {
		return mathematics + java + python + html;
	} 
	
	public Integer getAvg() {
		return mathematics + java + python + html/4;
	}
	
	
	public String getStatus() {
		String status;
		double avg=mathematics + java + python + html/4;
		
		if(avg>50) {
			status="Distinction";
		}
		else if(avg>35) {
			status="Pass";
		}
		else { 
			status="Fail";
		}
		return status; 
		}
	
	
	
	
	
//	 @ManyToOne                //many marks(sub) have 1 student 
//	 private Studentlogindetails student;
//	
}
// create table studentmarks (enrollment_no integer not null auto_increment, html integer not null, java integer not null, mathematics integer not null, python integer not null, primary key (enrollment_no)) engine=InnoDB
