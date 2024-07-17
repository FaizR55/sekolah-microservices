package com.sekolah.messaging.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sekolah.messaging.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {
	
	@Query(value = """
                       SELECT *
                       from student
                       Where (CASE WHEN 'name'=:type THEN name LIKE %:value% WHEN 'email'=:type THEN email LIKE %:value% WHEN 'password'=:type THEN password LIKE %:value% END)""",nativeQuery=true)
	List<User> findBySearchBy(@Param("type")String type,@Param("value")String value);

	@Query(value="SELECT * from student where email=?1 and password=?2",nativeQuery = true)
	String findByLogin(String email, String password);
	
	User findByName(String name); 

}
