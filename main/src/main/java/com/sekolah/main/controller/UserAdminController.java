package com.sekolah.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sekolah.main.common.Response;
import com.sekolah.main.dto.UserAdminDto;
import com.sekolah.main.entity.UserAdmin;
import com.sekolah.main.repository.UserAdminRepo;
import com.sekolah.main.service.UserAdminService;

@SuppressWarnings({ "unchecked", "rawtypes" })
@RestController
@RequestMapping("/api/main/admins")
public class UserAdminController {

    Response response = new Response();

    @Autowired
    private UserAdminService adminService;
    
    @Autowired
    private UserAdminRepo adminRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity getAllAdmins() {

        Object data = adminService.findAll();

        response.setStatus(true);
        response.setMessage(null);
        response.setData(data);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity getAdminById(@PathVariable Long id) {
        UserAdmin data = adminService.findById(id);
        if (data == null) {
            return ResponseEntity.notFound().build();
        }

        response.setStatus(true);
        response.setMessage(null);
        response.setData(data);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateAdmin(@PathVariable Long id, @Validated @RequestBody UserAdminDto userDto) {
        UserAdmin admin = adminService.findById(id);
        if (admin == null) {
            return ResponseEntity.notFound().build();
        }
        admin.setNamaDepan(userDto.getNamaDepan());
        admin.setNamaBelakang(userDto.getNamaBelakang());
        admin.setEmail(userDto.getEmail());
        admin.setPassword(passwordEncoder.encode(userDto.getPassword()));
        admin.setTanggalLahir(userDto.getTanggalLahir());
        admin.setJenisKelamin(userDto.getJenisKelamin());
        UserAdmin updatedAdmin = adminRepo.save(admin);

        response.setStatus(true);
        response.setMessage(null);
        response.setData(updatedAdmin);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAdmin(@PathVariable Long id) {
        UserAdmin admin = adminService.findById(id);
        if (admin == null) {
            return ResponseEntity.notFound().build();
        }
        adminService.deleteById(id);

        response.setStatus(true);
        response.setMessage("Delete success");
        response.setData(null);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}

