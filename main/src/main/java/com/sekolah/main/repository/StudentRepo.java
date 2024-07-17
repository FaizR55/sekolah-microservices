package com.sekolah.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sekolah.main.entity.Student;

public interface StudentRepo extends JpaRepository<Student, Long> {


    
}
