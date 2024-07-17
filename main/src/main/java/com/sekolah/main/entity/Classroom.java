package com.sekolah.main.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
        name = "class_students",
        joinColumns = @JoinColumn(name = "classroom_id"),
        inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> studentList;

    @ManyToMany
    @JoinTable(
        name = "class_subjects",
        joinColumns = @JoinColumn(name = "classroom_id"),
        inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<Subject> subjectList;
    
}
