# sekolah-microservices

Personal study on Java Spring Microservices with eureka server and spring gateway.<br />
4 Services in total : Main service, Messaging service, Eureka server, and Gateway service.<br />

## Main service
-CRUD function on sekolah data : student, teacher, classroom, subject<br />
-One classroom have many student, subject schedules assigned to classroom, teacher assigned to subject<br />
-API protected with JWT, user admin must register and login to access api first<br />

## Messaging service
-Simple messaging with crud<br />
-Login function still simple password matching from db<br />
-Registered student from main service can login to check inbox and send message<br />
-Can send message to multiple emails<br />

## Gateway service
-Can redirect api hit to both service above<br />

## Eureka server service
-Can monitor services status on localhost:8761<br />

Steps :<br />
-Run eureka server service<br />
-Run Gateway service<br />
-Run main server service<br />
-Run messaging server service<br />

Postman file attached

