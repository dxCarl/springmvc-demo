package com.yucong.demo;

import org.springframework.stereotype.Service;

import com.yucong.demo.domain.User;

@Service
public class UserService {
   
	public void createUser(User user){
		System.out.println("save user.");
	}

	public User getUserById(String userId) {
		User user = new User();
		user.setUserId(userId);
		user.setUserName("test");
		return user;
	}
	
}
