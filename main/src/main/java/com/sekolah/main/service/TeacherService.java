package com.sekolah.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sekolah.main.entity.Teacher;
import com.sekolah.main.repository.TeacherRepo;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepo teacherRepo;

    public List<Teacher> getAllTeachers() {
        return teacherRepo.findAll();
    }

    public Teacher getTeacherById(Long id) {
        return teacherRepo.findById(id).orElse(null);
    }

    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepo.save(teacher);
    }

    public void deleteTeacher(Long id) {
        teacherRepo.deleteById(id);
    }
}
