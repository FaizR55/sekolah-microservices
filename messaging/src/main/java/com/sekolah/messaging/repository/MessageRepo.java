package com.sekolah.messaging.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sekolah.messaging.entity.Message;

public interface MessageRepo extends JpaRepository<Message, Long> {
	
    @Query(value="SELECT * from message where receiver=?1 ORDER BY time_stamp DESC",nativeQuery = true)
	List<Message> findMsg(String email);

}