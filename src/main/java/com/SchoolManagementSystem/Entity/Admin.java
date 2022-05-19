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
@Table(name="admin")
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adminId;
	
	@NotBlank(message = "name field is required!!!")
	@Size(min =4,max=15, message = "name shoul be in between 4-15 characters")
	private String name;
	
	@NotBlank(message = "role field is required!!!")
	private String role;
	
	@NotBlank(message = "email is mandatory")
	@Pattern(regexp = "^[A-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message = "Invalid email!!!")
	@Column(length=50,nullable = false)
	private String email;
	
	@NotBlank(message = "password is mandatory")
	private String password;
	
	
	//@NotBlank(message = "Announce Something")
	private String Notice;
	
	@ManyToOne
	 private Teacher teacher;

	@ManyToOne
	 private Studentlogindetails student;
	
	//create table admin (admin_id integer not null auto_increment, email varchar(50) not null, name varchar(15), password varchar(255), role varchar(255), student_roll_no integer, teacher_teacher_id integer, primary key (admin_id)) engine=InnoDB

	
}
