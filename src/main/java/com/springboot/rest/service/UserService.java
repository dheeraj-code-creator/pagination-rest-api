package com.springboot.rest.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.rest.entity.User;
import com.springboot.rest.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public Page<User> getAllUserInfo(Pageable pageRequest) {
		List<User> userList = Arrays.asList(
				new User("111", "First Demo"),
				new User("222", "Second Demo"),
				new User("333", "Third Demo"),
				new User("444", "Fourth Demo"),
				new User("555", "Fifth Demo"),
				new User("666", "Sixth Demo"),
				new User("777", "Seventh Demo"),
				new User("888", "Eighth Demo"));
		userRepository.saveAll(userList);
		Page<User> userDataList = userRepository.findAll(pageRequest);
		return userDataList;
	}
}
