package com.SchoolManagementSystem.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table (name="student")
public class Studentlogindetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rollNo;
	
	@NotBlank(message = "name field is required!!!")
	@Size(min =4,max=15, message = "name should be in between 4-15 characters")
	//@Column(length=50,nullable = false)
	private String fullName;
	
	@NotBlank(message = "name field is required!!!")
	private String fatherName;
	
	@NotBlank(message = "name field is required!!!")
	private String motherName;
	
	@NotBlank(message = "address field is required!!!")
	private String studentAddress;
	
	@NotBlank(message = "percentage field is required!!!")
	private String percentage;
	
	@NotBlank(message = "contact number required!!!")
	private String contactNumber;
	
	@NotBlank(message = "email is mandatory")
	@Pattern(regexp = "^[A-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message = "Invalid email!!!")
	private String studentEmail;
	
	
	@NotBlank(message = "enter password")
	private String password;
	
	
    private String image;

   @OneToMany(mappedBy="student",cascade=CascadeType.ALL)
   private List<Teacher> teacher;
    
   
   @OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
   private List<Studentmarks> studentmarks =new ArrayList();
    
	@Override
	public String toString() {
		return "Studentlogindetails [rollNo=" + rollNo + ", fullName=" + fullName + ", fatherName=" + fatherName
				+ ", motherName=" + motherName + ", studentAddress=" + studentAddress + ", percentage=" + percentage
				+ ", contactNumber=" + contactNumber + ", studentEmail=" + studentEmail + ", password=" + password
				+ ", image=" + image + "]";
	}


	

    
}
