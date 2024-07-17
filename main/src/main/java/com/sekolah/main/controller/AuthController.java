package com.sekolah.main.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sekolah.main.config.JwtService;
import com.sekolah.main.dto.LoginDto;
import com.sekolah.main.dto.UserAdminDto;
import com.sekolah.main.entity.UserAdmin;
import com.sekolah.main.service.UserAdminService;

@SuppressWarnings({"rawtypes"})
@RestController
@RequestMapping("/api/main/auth")
public class AuthController {

    @Autowired
    private UserAdminService userService;
    
    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity authenticate(@RequestBody LoginDto loginUserDto) {

        Map<String, Object> data = new HashMap<>();

        UserAdmin authenticatedUser;
        try {
            authenticatedUser = userService.authenticate(loginUserDto);
        } catch (Exception e) {
            data.put("status", false);
            data.put("message", "Wrong email or pass");
    
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(data);
        }

        String jwtToken = jwtService.generateToken(authenticatedUser);

        data.put("status", true);
        data.put("message", "Login Success");
        data.put("token", jwtToken);
        data.put("exp", jwtService.getExpirationTime());

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(data);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserAdminDto userDto){

        Map<String, Object> data = new HashMap<>();

        Object register = userService.saveUser(userDto);
        if(register == null){
            data.put("status", false);
            data.put("message", "Error occured");
    
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(data);
        }else{
            data.put("status", true);
            data.put("message", "User registered successfully");
    
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(data);
        }

    }
}
