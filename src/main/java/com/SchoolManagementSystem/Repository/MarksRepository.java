package com.SchoolManagementSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SchoolManagementSystem.Entity.Studentmarks;


public interface MarksRepository extends JpaRepository<Studentmarks,Integer> {

}
