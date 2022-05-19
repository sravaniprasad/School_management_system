package com.SchoolManagementSystem.ServiceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SchoolManagementSystem.Entity.Admin;

import com.SchoolManagementSystem.Entity.Teacher;
import com.SchoolManagementSystem.Repository.AdminRepository;
import com.SchoolManagementSystem.Service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{
@Autowired
AdminRepository adminrepository;
	
@Override
	public void addAdmin(Admin admin) {
		// TODO Auto-generated method stub
		this.adminrepository.save(admin);
	}

@Override
public Admin checkadminLogin(String name, String password) {
		return this.adminrepository.findByNameAndPassword(name, password);
}


@Override
public List<Admin> getAllAdmin() {
	// TODO Auto-generated method stub
	return adminrepository.findAll();
}

@Override
public Admin getAdminByadminId(int adminId) {
	// TODO Auto-generated method stub
	return adminrepository.findById(adminId).get();
}

@Override
public void updateAdmin(Admin admin, int adminId) {
	Admin admin1=adminrepository.findById(adminId).get();
	admin1.setName(admin1.getName());
	admin1.setRole(admin1.getRole());
	admin1.setEmail(admin1.getEmail());
	admin1.setPassword(admin1.getPassword());
	admin1.setNotice(admin1.getNotice());
	this.adminrepository.save(admin1);
	
}
}
