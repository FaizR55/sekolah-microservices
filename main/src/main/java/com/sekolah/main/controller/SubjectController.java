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
import com.sekolah.main.entity.Subject;
import com.sekolah.main.service.SubjectService;


@SuppressWarnings({ "unchecked", "rawtypes" })
@RestController
@RequestMapping("/api/main/subject")
public class SubjectController {

    Response response = new Response();

    @Autowired
    private SubjectService subjectService;


    @GetMapping
    public ResponseEntity getAllSubjects() {

        Object data = subjectService.getAllSubjects();

        response.setStatus(true);
        response.setMessage(null);
        response.setData(data);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity getSubjectById(@PathVariable("id") Long id) {

        Object data = subjectService.getSubjectById(id);

        response.setStatus(true);
        response.setMessage(null);
        response.setData(data);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }


    @PostMapping
    public ResponseEntity saveSubject(@RequestBody Subject subject) {

        Object data = subjectService.saveSubject(subject);

        response.setStatus(true);
        response.setMessage(null);
        response.setData(data);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteSubject(@PathVariable("id") Long id) {
        subjectService.deleteSubject(id);

        response.setStatus(true);
        response.setMessage(null);
        response.setData("OK");

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PostMapping("/addteachertosubject")
    public ResponseEntity addTeacherToSubject(@RequestBody Map<String, Object> userMap) {
        System.out.println(userMap);
        Long subjectId = Long.valueOf(userMap.get("subjectId").toString());
        Long teacherId = Long.valueOf(userMap.get("teacherId").toString());
        subjectService.addTeacherToSubject(subjectId, teacherId);

        response.setStatus(true);
        response.setMessage(null);
        response.setData("OK");

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
    
}
