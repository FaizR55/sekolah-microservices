package com.sekolah.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sekolah.main.entity.Classroom;

public interface ClassroomRepo extends JpaRepository<Classroom, Long> {


    
}
