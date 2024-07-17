package com.sekolah.messaging.entity;

import java.text.SimpleDateFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@NoArgsConstructor
@Table(name="message")
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String sender;

    @NonNull
    private String subject;

    @NonNull
    @Column(columnDefinition="TEXT")
    private String body;

    private String receiver;
    
    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
}
