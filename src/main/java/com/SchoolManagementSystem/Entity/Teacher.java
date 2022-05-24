package com.SchoolManagementSystem.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="teacher")
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int teacherId;
	
	@NotBlank(message = "name field is required!!!")
	@Size(min =4,max=15, message = "name shoul be in between 4-15 characters")
	private String teacherName;
	
	@NotBlank(message = "Role field is required!!!")
	private String teacherRole;
	
	@NotBlank(message = "email is mandatory")
	@Pattern(regexp = "^[A-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message = "Invalid email!!!")
	@Column(length=50,nullable = false)
	private String teacherEmail;
	
	@NotBlank(message = "address field is required!!!")
	@Size(min =4,max=15, message = "name shoul be in between 4-15 characters")
	private String teacherAddress;
	
	@NotBlank(message = "contact field is required!!!")
	private String teacherContact;
	
	@NotBlank(message = "contact field is required!!!")
	private String teacherAlternateContact;
	
	@NotBlank(message = "Age is mandatory")
	private String teacherAge;
	
	private String teacherPassword;
	
	
	private String image;

	 @ManyToOne              // one teacher has many students
	 private Studentlogindetails student;
	
//	@ManyToOne
//	private Studentlogindetails studentlogindetails;
}
