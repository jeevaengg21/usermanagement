package com.jee.gen.users;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jee.gen.users.model.PasswordData;
import com.jee.gen.users.model.LoginData;
import com.jee.gen.users.model.Users;
import com.jee.gen.users.model.UsersLite;
import com.sun.istack.NotNull;
@RestController
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/users")
	public List<UsersLite> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<UsersLite> getUserById(@NotNull @NotBlank@PathVariable("id") Long id) {
		UsersLite u = userService.getUserById(id);
		if (u == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(u, HttpStatus.OK);
		}
	}

	@PostMapping("/users")
	public ResponseEntity<UsersLite> saveUser(@Valid @RequestBody Users user) {
		UsersLite u =  userService.saveUser(user);	
		return new ResponseEntity<>(u, HttpStatus.OK);		
	}

	@PutMapping("/users/{id}/{name}")
	public ResponseEntity<?> updateUsername(@NotNull @NotBlank @PathVariable("id") Long id,
			@NotNull @NotBlank @PathVariable("name") String name) {
		boolean status=userService.updateUsername(id, name);
		if(status) {		
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}		
	}
	
	@PutMapping("/users/password/{id}")
	public ResponseEntity<?> changePassword(@NotNull @NotBlank @PathVariable("id") Long id, 
		@Valid	@RequestBody PasswordData pwdData) {
		boolean status=userService.changePassword(id, pwdData);		
		if(status) {
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUserById(@NotNull @NotBlank @PathVariable("id") Long id) {
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@PostMapping("/users/login")
	public ResponseEntity<String> userLogin(@Valid @RequestBody LoginData loginData) {
		UsersLite u= userService.userLogin(loginData);
		if(u==null) {
			return new ResponseEntity<>("Invalid credentials" ,HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<>("Login successful", HttpStatus.OK);
		}
	}

}