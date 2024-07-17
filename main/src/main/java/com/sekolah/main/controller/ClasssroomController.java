package com.sekolah.main.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sekolah.main.common.Response;
import com.sekolah.main.entity.Classroom;
import com.sekolah.main.service.ClassroomService;


@SuppressWarnings({ "unchecked", "rawtypes" })
@RestController
@RequestMapping("/api/main/classroom")
public class ClasssroomController {

    Response response = new Response();

    @Autowired
    private ClassroomService classService;


    @GetMapping
    public ResponseEntity getAllClassrooms() {

        Object data = classService.getAllClasses();

        response.setStatus(true);
        response.setMessage(null);
        response.setData(data);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity getClassroomById(Long id) {

        Object data = classService.getClassById(id);

        response.setStatus(true);
        response.setMessage(null);
        response.setData(data);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }


    @PostMapping
    public ResponseEntity saveClassroom(@RequestBody Classroom classroom) {

        Object data = classService.saveClass(classroom);

        response.setStatus(true);
        response.setMessage(null);
        response.setData(data);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteClassroom(@PathVariable("id") Long id) {
        classService.deleteClass(id);

        response.setStatus(true);
        response.setMessage(null);
        response.setData("OK");

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PostMapping("/addstudenttoclass")
    public ResponseEntity addStudentToClass(@RequestBody Map<String, Object> userMap) {
        Long classId = Long.valueOf(userMap.get("classId").toString());
        Long studentId = Long.valueOf(userMap.get("studentId").toString());
        classService.addStudentToClass(classId, studentId);

        response.setStatus(true);
        response.setMessage(null);
        response.setData("OK");

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PostMapping("/addsubjecttoclass")
    public ResponseEntity addSubjectToClass(@RequestBody Map<String, Object> userMap) {
        Long classId = Long.valueOf(userMap.get("classId").toString());
        Long subjectId = Long.valueOf(userMap.get("subjectId").toString());
        classService.addSubjectToClass(classId, subjectId);

        response.setStatus(true);
        response.setMessage(null);
        response.setData("OK");

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
    
}
