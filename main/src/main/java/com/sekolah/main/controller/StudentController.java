package com.sekolah.main.controller;

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
import com.sekolah.main.entity.Student;
import com.sekolah.main.service.StudentService;

@SuppressWarnings({ "unchecked", "rawtypes" })
@RestController
@RequestMapping("/api/main/student")
public class StudentController {

    Response response = new Response();

    @Autowired
    private StudentService studentService;
    

    @GetMapping
    public ResponseEntity getAllStudents() {

        Object data = studentService.getAllStudents();

        response.setStatus(true);
        response.setMessage(null);
        response.setData(data);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity getStudentById(@PathVariable("id") Long id) {

        Object data = studentService.getStudentById(id);

        response.setStatus(true);
        response.setMessage(null);
        response.setData(data);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }


    @PostMapping
    public ResponseEntity saveStudent(@RequestBody Student student) {

        Object data = studentService.saveStudent(student);

        response.setStatus(true);
        response.setMessage(null);
        response.setData(data);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudent(@PathVariable("id") Long id) {
        System.out.println(id);
        studentService.deleteStudent(id);

        response.setStatus(true);
        response.setMessage(null);
        response.setData("OK");

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

}
