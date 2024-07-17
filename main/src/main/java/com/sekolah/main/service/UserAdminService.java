package com.sekolah.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sekolah.main.dto.LoginDto;
import com.sekolah.main.dto.UserAdminDto;
import com.sekolah.main.entity.UserAdmin;
import com.sekolah.main.repository.UserAdminRepo;

@Service
public class UserAdminService {

    @Autowired
    private UserAdminRepo userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AuthenticationManager authenticationManager;


    public UserAdmin authenticate(LoginDto input) {
        if(!userRepository.existsByEmail(input.getEmail())){
            System.out.println("EMAIL NOT FOUND");
        }
        authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            input.getEmail(),
                            input.getPassword()
                    )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
    
    public Object saveUser(UserAdminDto userDto) {

        try {
            if(userRepository.existsByEmail(userDto.getEmail())){
                return null;
            }
    
            UserAdmin user = new UserAdmin();
    
            user.setNamaDepan(userDto.getNamaDepan());
            user.setNamaBelakang(userDto.getNamaBelakang());
            user.setEmail(userDto.getEmail());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setTanggalLahir(userDto.getTanggalLahir());
            user.setJenisKelamin(userDto.getJenisKelamin());
            userRepository.save(user);
    
            return user;
        } catch (Exception e) {
            System.out.println("Error occured : "+e.getMessage());
            return null;
        }

    }

    public Object updateUser(UserAdminDto userDto) {
        UserAdmin user = findById(userDto.getId());
        if (user == null) {
            return null;
        }
        user.setNamaDepan(userDto.getNamaDepan());
        user.setNamaBelakang(userDto.getNamaBelakang());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setTanggalLahir(userDto.getTanggalLahir());
        user.setJenisKelamin(userDto.getJenisKelamin());
        UserAdmin updatedAdmin = userRepository.save(user);

        return updatedAdmin;
    }

    public UserAdmin findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<UserAdmin> findAll() {
        return userRepository.findAll();
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public UserAdmin findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
