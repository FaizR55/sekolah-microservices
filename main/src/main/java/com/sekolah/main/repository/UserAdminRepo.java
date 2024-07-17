package com.sekolah.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sekolah.main.entity.UserAdmin;

public interface UserAdminRepo extends JpaRepository<UserAdmin, Long> {

    UserAdmin findById(int id);

    UserAdmin findUserByEmail(String email);

    Boolean existsByEmail(String email);

    Optional<UserAdmin> findByEmail(String email);
    
}
