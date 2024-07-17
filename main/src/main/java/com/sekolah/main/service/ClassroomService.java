package com.sekolah.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sekolah.main.entity.Classroom;
import com.sekolah.main.entity.Student;
import com.sekolah.main.entity.Subject;
import com.sekolah.main.repository.ClassroomRepo;
import com.sekolah.main.repository.StudentRepo;
import com.sekolah.main.repository.SubjectRepo;

@Service
public class ClassroomService {

    @Autowired
    private ClassroomRepo classRepo;

    @Autowired
    private StudentRepo studentRepo;
    
    @Autowired
    private SubjectRepo subjectRepo;

    public List<Classroom> getAllClasses() {
        return classRepo.findAll();
    }

    public Classroom getClassById(Long id) {
        return classRepo.findById(id).orElse(null);
    }

    public Classroom saveClass(Classroom classroom) {
        return classRepo.save(classroom);
    }

    public void deleteClass(Long id) {
        classRepo.deleteById(id);
    }

    public Classroom addStudentToClass(Long classId, Long studentId) {
        Optional<Classroom> classOpt = classRepo.findById(classId);
        Optional<Student> studentOpt = studentRepo.findById(studentId);

        if (classOpt.isPresent() && studentOpt.isPresent()) {
            Classroom classEntity = classOpt.get();
            Student studentEntity = studentOpt.get();

            classEntity.getStudentList().add(studentEntity);
            return classRepo.save(classEntity);
        }

        return null;
    }

    public Classroom addSubjectToClass(Long classId, Long subjectId) {
        Optional<Classroom> classOpt = classRepo.findById(classId);
        Optional<Subject> subjectOpt = subjectRepo.findById(subjectId);

        if (classOpt.isPresent() && subjectOpt.isPresent()) {
            Classroom classEntity = classOpt.get();
            Subject subjectEntity = subjectOpt.get();

            classEntity.getSubjectList().add(subjectEntity);
            return classRepo.save(classEntity);
        }

        return null;
    }
}
