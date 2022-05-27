package com.SchoolManagementSystem.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
	@Max(value=50,message="Marks Should be in 50")
	private Integer mathematics;
	
	@NotNull(message = " please provide marks!!!")
	@Max(value=50,message="Marks Should be in 50")
	private Integer java;
	
	@NotNull(message = " please provide marks!!!")
	@Max(value=50,message="Marks Should be in 50")
	private Integer python;
	
	@NotNull(message = " please provide marks!!!")
	@Max(value=50,message="Marks Should be in 50")
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
	
	public double getAvg() {
		int total=mathematics + java + python + html;
		double avg= total /4;
		return avg;
	}
	
	public Integer getPercentage() {
		int total=mathematics + java + python + html;
		int percentage=total /2;
		return percentage;
	}
	public String getStatus() {
		String status;
		int total=mathematics + java + python + html;
		int percentage=total /2;
		
		if(percentage>70) {
			status="Distinction";
		}
		else if(percentage>35) {
			status="Pass";
		}
		else { 
			
			status="Fail-Not Eligible";
		}
		return status; 
		}
	
	
	
	
	
//	 @ManyToOne                //many marks(sub) have 1 student 
//	 private Studentlogindetails student;
//	
}
// create table studentmarks (enrollment_no integer not null auto_increment, html integer not null, java integer not null, mathematics integer not null, python integer not null, primary key (enrollment_no)) engine=InnoDB
