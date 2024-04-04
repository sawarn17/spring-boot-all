package com.appinventiv.controller;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.appinventiv.dto.UserDTO;
import com.appinventiv.entity.User;
import com.appinventiv.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping(value = "/users", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, produces = "application/json")
	public ResponseEntity<Object> saveUsers(@RequestParam(value = "files") MultipartFile[] files) throws Exception {
		for (MultipartFile file : files) {
			userService.saveUsers(file);
		}
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping(value = "/users", produces = "application/json")
	public CompletableFuture<ResponseEntity<Object>> findAllUsers() {
		return userService.getAllUsers().thenApply(ResponseEntity::ok);
	}
	
	@GetMapping(value = "/getAllUsersByThread", produces = "application/json")
	public ResponseEntity<Object> getUsers(){
		CompletableFuture<List<User>> allUsers1 = userService.getAllUsers();
		CompletableFuture<List<User>> allUsers2 = userService.getAllUsers();
		CompletableFuture<List<User>> allUsers3 = userService.getAllUsers();
		CompletableFuture.allOf(allUsers1, allUsers2, allUsers3).join();
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@PutMapping("/users")
	public ResponseEntity<Object> updateUserName(@RequestBody UserDTO userDTO){
		User user = userService.updateUser(userDTO);
		if(Objects.nonNull(user)) {
			return new ResponseEntity<Object>(user, HttpStatus.OK);
		}else {
			return new ResponseEntity<Object>(user, HttpStatus.EXPECTATION_FAILED);
		}
	}
}
