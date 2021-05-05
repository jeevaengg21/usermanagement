package com.jee.gen.users;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.jee.gen.users.model.Users;

public interface UserRepository extends CrudRepository<Users, Long>{
	List<Users> findByEmail(String email);
	
}
