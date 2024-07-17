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
import com.sekolah.main.entity.Teacher;
import com.sekolah.main.service.TeacherService;

@SuppressWarnings({ "unchecked", "rawtypes" })
@RestController
@RequestMapping("/api/main/teacher")
public class TeacherController {

    Response response = new Response();

    @Autowired
    private TeacherService teacherService;


    @GetMapping
    public ResponseEntity getAllTeachers() {

        Object data = teacherService.getAllTeachers();

        response.setStatus(true);
        response.setMessage(null);
        response.setData(data);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity getTeacherById(@PathVariable("id") Long id) {

        Object data = teacherService.getTeacherById(id);

        response.setStatus(true);
        response.setMessage(null);
        response.setData(data);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }


    @PostMapping
    public ResponseEntity saveTeacher(@RequestBody Teacher teacher) {

        Object data = teacherService.saveTeacher(teacher);

        response.setStatus(true);
        response.setMessage(null);
        response.setData(data);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteTeacher(@PathVariable("id") Long id) {
        teacherService.deleteTeacher(id);

        response.setStatus(true);
        response.setMessage(null);
        response.setData("OK");

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

}
