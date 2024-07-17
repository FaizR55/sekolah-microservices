package com.sekolah.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sekolah.main.entity.Subject;
import com.sekolah.main.entity.Teacher;
import com.sekolah.main.repository.SubjectRepo;
import com.sekolah.main.repository.TeacherRepo;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepo subjectRepo;

    @Autowired
    private TeacherRepo teacherRepo;

    public List<Subject> getAllSubjects() {
        return subjectRepo.findAll();
    }

    public Subject getSubjectById(Long id) {
        return subjectRepo.findById(id).orElse(null);
    }

    public Subject saveSubject(Subject subject) {
        return subjectRepo.save(subject);
    }

    public void deleteSubject(Long id) {
        subjectRepo.deleteById(id);
    }

    public Object addTeacherToSubject(Long subjectId, Long teacherId) {
        Optional<Subject> subjectOpt = subjectRepo.findById(subjectId);
        Optional<Teacher> teacherOpt = teacherRepo.findById(teacherId);

        if (teacherOpt.isPresent() && subjectOpt.isPresent()) {
            Teacher teacherEntity = teacherOpt.get();
            Subject subjectEntity = subjectOpt.get();

            subjectEntity.setTeacher(teacherEntity);
            return subjectRepo.save(subjectEntity);
        }

        return null;
    }
}
