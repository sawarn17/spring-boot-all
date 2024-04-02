package com.appinventiv.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.appinventiv.entity.User;
import com.appinventiv.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
	
	@Autowired UserRepository userRepository;
	
	@Async
	public CompletableFuture<List<User>> saveUsers(MultipartFile multipartFile) throws Exception{
		long startTime = System.currentTimeMillis();
		List<User> allUsers = parseCSVFile(multipartFile);
		log.info("We are processing list of user which size are {} and Thread Name are {}",
				allUsers.size(), Thread.currentThread().getName());
		allUsers = userRepository.saveAll(allUsers);
		long endTime = System.currentTimeMillis();
		log.info("Total Time taking to save the record are {} " , endTime -startTime);
		return CompletableFuture.completedFuture(allUsers);
	}
	
	 private List<User> parseCSVFile(final MultipartFile file) throws Exception {
	        final List<User> users = new ArrayList<>();
	        try {
	            try (final BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
	                String line;
	                while ((line = br.readLine()) != null) {
	                    final String[] data = line.split(",");
	                    final User user = new User();
	                    user.setName(data[0]);
	                    user.setEmail(data[1]);
	                    user.setGender(data[2]);
	                    users.add(user);
	                }
	                return users;
	            }
	        } catch (final IOException e) {
	            log.error("Failed to parse CSV file {}", e);
	            throw new Exception("Failed to parse CSV file {}", e);
	        }
	    }
	 
	 @Async
	 public CompletableFuture<List<User>> getAllUsers(){
		 List<User> allUsers = userRepository.findAll();
		 log.info("Getting List of users by thread name "+ Thread.currentThread().getName());
		 return CompletableFuture.completedFuture(allUsers);
	 }

}
