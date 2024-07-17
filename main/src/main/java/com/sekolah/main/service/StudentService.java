package com.sekolah.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sekolah.main.entity.Classroom;
import com.sekolah.main.entity.Student;
import com.sekolah.main.repository.ClassroomRepo;
import com.sekolah.main.repository.StudentRepo;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private ClassroomRepo classRepo;
    
    // @Autowired
    // private PasswordEncoder passwordEncoder;

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepo.findById(id).orElse(null);
    }

    public Student saveStudent(Student student) {

        // String password = student.getPassword();
        // student.setPassword(passwordEncoder.encode(password));
        studentRepo.save(student);

        return student;
    }

    public void deleteStudent(Long id) {
        Optional<Student> studentOpt = studentRepo.findById(id);
        Student student = studentOpt.get();
        Classroom lcs = student.getClassroom();
        classRepo.deleteById(lcs.getId());
        studentRepo.deleteById(id);
    }
}
