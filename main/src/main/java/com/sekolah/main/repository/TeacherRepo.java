package com.sekolah.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sekolah.main.entity.Teacher;

public interface TeacherRepo extends JpaRepository<Teacher, Long> {


    
}
