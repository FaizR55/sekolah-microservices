package com.sekolah.messaging.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sekolah.messaging.common.Response;
import com.sekolah.messaging.dto.LoginDto;
import com.sekolah.messaging.entity.User;
import com.sekolah.messaging.repository.MessageRepo;
import com.sekolah.messaging.repository.UserRepo;
import com.sekolah.messaging.service.LoginService;


@SuppressWarnings({"unchecked", "rawtypes"})
@RestController
@RequestMapping("/api/messaging")
public class UserController {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	MessageRepo msgRepo;
	
    @Autowired
    private LoginService loginServ;

	Response response = new Response();

	@PostMapping("/login")
	public ResponseEntity<Response> loginUser(@RequestBody LoginDto dto) {

		List<String> auth = loginServ.login(dto.getEmail(), dto.getPassword());

		if (auth.get(0).equals("sukses")) {

		String user = auth.get(1);
		String[] ary = user.split(",");
		Map<String, Object> msg = new HashMap<>();
		
		msg.put("id", ary[0]);
        msg.put("nama", ary[1]);
        msg.put("email", ary[3]);

		response.setStatus("Sukses");
        response.setMessage("Selamat datang user");
        response.setData(msg);

		return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
		}else {
		response.setStatus("Gagal");
		response.setMessage("Autentikasi gagal");
		response.setData("-");

		return ResponseEntity
				.status(HttpStatus.FORBIDDEN)
				.contentType(MediaType.APPLICATION_JSON)
				.body(response);
		}
	}

	@PostMapping("/inbox")
	public ResponseEntity<Response> inboxUser(@RequestBody LoginDto dto) {

		List<String> auth = loginServ.login(dto.getEmail(), dto.getPassword());

		if (auth.get(0).equals("sukses")) {

		response.setStatus("Sukses");
        response.setMessage("Inbox email anda :");
        response.setData(msgRepo.findMsg(dto.getEmail()));

		return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
		}else {
		response.setStatus("Gagal");
		response.setMessage("Autentikasi gagal");
		response.setData("-");

		return ResponseEntity
				.status(HttpStatus.FORBIDDEN)
				.contentType(MediaType.APPLICATION_JSON)
				.body(response);
		}
	}

	@GetMapping("/searchby/{type}/{value}")
	public List<User> getSearchBy(@PathVariable("type")String type, @PathVariable("value") String value) {
		return userRepo.findBySearchBy(type, value);
	}

	@GetMapping("/name/{value}")
	public User getByName(@PathVariable("value") String value) {
		return userRepo.findByName(value);
	}
	
	@GetMapping("/user")
	public List<User> getAll() {
		return userRepo.findAll();
	}
	
	@PostMapping("/user/edit/{id}")
	public String updateUser(@PathVariable String id, @RequestBody User user) {
		user.setId(Long.parseLong(id));
		userRepo.save(user);
		return "Update user berhasil";
	}

	// @PostMapping("/register/")
	// public String addUser(@RequestBody User user) {
	// 	try {
	// 		userRepo.save(user);
	// 		return "User baru berhasil dibuat";
	// 	} catch (Exception e) {
	// 		return "Terjadi kesalahan parameter";
	// 	}
	// }

}