package com.jee.gen.users;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jee.gen.users.model.PasswordData;
import com.jee.gen.users.model.LoginData;
import com.jee.gen.users.model.Users;
import com.jee.gen.users.model.UsersLite;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;

	public List<UsersLite> getAllUsers() {
		List<UsersLite> users = new ArrayList<UsersLite>();
		userRepo.findAll().forEach(u -> users.add(new UsersLite(u)));
		return users;
	}

	public UsersLite getUserById(Long id) {
		Optional<Users> user = userRepo.findById(id);
		if (user.isPresent()) {
			return new UsersLite(user.get());
		} else {
			return null;
		}
	}

	public UsersLite saveUser(Users user) {
		String ePass = encryptPassword(user.getPassword());
		user.setEmail(user.getEmail().toLowerCase());
		user.setPassword(ePass);
		user.setLldate(null);
		userRepo.save(user);
		/**
		 * duplicate account handling logic can be included
		 **/
		return new UsersLite(user);

	}

	public Boolean updateUsername(Long id, String name) {
		Optional<Users> user = userRepo.findById(id);
		if (user.isPresent()) {
			Users u = user.get();
			u.setName(name);
			userRepo.save(u);
			return true;
		}
		return false;
	}

	public Boolean changePassword(Long id, PasswordData changePassword) {
		Optional<Users> user = userRepo.findById(id);
		if (user.isPresent()) {
			Users u = user.get();
			String eOldPass = encryptPassword(changePassword.getOldPassword());
			if (u.getPassword().equals(eOldPass)) {
				String eNewPass = encryptPassword(changePassword.getNewPassword());
				u.setPassword(eNewPass);
				userRepo.save(u);
				return true;
			}
		}
		return false;
	}

	public void deleteUser(Long id) {
		userRepo.deleteById(id);
	}

	public UsersLite userLogin(LoginData loginData) {
		List<Users> users = userRepo.findByEmail(loginData.getEmail().toLowerCase());		
		if (!users.isEmpty()) {
			Users user = users.get(0);
			String ePass = encryptPassword(loginData.getPassword());
			if (ePass.equals(user.getPassword())) {
				user.setLldate(new Date());
				userRepo.save(user);
				return new UsersLite(user);
			}

		}
		return null;
	}

	private String encryptPassword(String password) {
		/**
		 * One way encryption approach.. any Encryption library could be used. 
		 **/
		return Base64.getEncoder().encodeToString(password.getBytes());
	}

}
