package com.SchoolManagementSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SchoolManagementSystem.Entity.Admin;

public interface AdminRepository extends JpaRepository<Admin,Integer> {

	public Admin findByNameAndPassword(String name,String password);
}
