# sekolah-microservices

Personal study on Java Spring Microservices with eureka server and spring gateway.
4 Services in total : Main service, Messaging service, Eureka server, and Gateway service.

## Main service
-CRUD function on sekolah data : student, teacher, classroom, subject
-One classroom have many student, subject schedules assigned to classroom, teacher assigned to subject
-API protected with JWT, user admin must register and login to access api first

## Messaging service
-Simple messaging with crud
-Login function still simple password matching from db
-Registered student from main service can login to check inbox and send message
-Can send message to multiple emails

## Gateway service
-Can redirect api hit to both service above

## Eureka server service
-Can monitor services status on localhost:8761

Steps :
-Run eureka server service
-Run Gateway service
-Run main server service
-Run messaging server service

Postman file attached

