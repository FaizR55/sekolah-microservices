package com.sekolah.main.dto;

import java.sql.Date;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UserAdminDto {

    private Long id;

    private String namaDepan;

    private String namaBelakang;

    @Email
    private String email;

    private String password;

    private Date tanggalLahir;

    private String jenisKelamin;
    
}
