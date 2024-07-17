package com.sekolah.messaging.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sekolah.messaging.repository.UserRepo;
import com.sekolah.messaging.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	UserRepo userRepo;
    
    @Override
    public List<String> login(String email, String password){

        List<String> data = new ArrayList<>();

        String user = userRepo.findByLogin(email, password);
        if(user != null && !user.isEmpty()){
            data.add("sukses");
            data.add(user.toString());

            return data;
        }else {
            data.add("gagal");
            
            return data;
        }

    }

}