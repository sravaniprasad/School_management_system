package com.SchoolManagementSystem.Service;

import java.util.List;

import com.SchoolManagementSystem.Entity.Admin;
import com.SchoolManagementSystem.Entity.Studentlogindetails;

import com.SchoolManagementSystem.Entity.Teacher;

public interface AdminService {

	List<Admin> getAllAdmin();
	
	public void addAdmin(Admin admin);
	
	public Admin checkadminLogin(String name,String password);

	Admin getAdminByadminId(int adminId);
	void updateAdmin(Admin admin, int adminId);


}
