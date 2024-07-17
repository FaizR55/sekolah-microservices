package com.sekolah.messaging.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sekolah.messaging.common.Response;
import com.sekolah.messaging.entity.Message;
import com.sekolah.messaging.repository.MessageRepo;
import com.sekolah.messaging.service.LoginService;


@SuppressWarnings({"unchecked", "rawtypes"})
@RestController
@RequestMapping("/api/messaging")
public class MessageController {

    @Autowired
	MessageRepo msgRepo;
	
    @Autowired
    private LoginService loginServ;

	Response response = new Response();

    @PostMapping("/send/")
	public ResponseEntity<Response> sendMessage(@RequestBody Message msg , @RequestParam String email, @RequestParam String password, @RequestParam String[] receiver) {
		
		List<String> auth = loginServ.login(email, password);
		if (auth.get(0).equals("sukses")) {
			String subject = msg.getSubject();
			String body = msg.getBody();
		if (receiver.length > 1){
			for (String i : receiver) {
				Message msgloop = new Message();
				msgloop.setSender(email);
				msgloop.setSubject(subject);
				msgloop.setBody(body);
				msgloop.setReceiver(i);
				msgRepo.save(msgloop);
			  }
			  Map<String, Object> map = new HashMap<>();
				map.put("sender", email);
				map.put("subject", subject);
				map.put("body", body);
				map.put("receiver", Arrays.toString(receiver));
				
				response.setStatus("Sukses");
				response.setMessage("Pesan berhasil dikirim");
				response.setData(map);
			return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
			}else{
				msg.setSender(email);
				msg.setReceiver(receiver[0]);
				msgRepo.save(msg);
				response.setStatus("Sukses");
				response.setMessage("Pesan berhasil dikirim");
				response.setData(msg);
			return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
			}
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
}
