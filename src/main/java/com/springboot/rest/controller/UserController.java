package com.springboot.rest.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest.entity.User;
import com.springboot.rest.service.ConverterService;
import com.springboot.rest.service.UserService;

@RestController
@RequestMapping(value = "/userinfo")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ConverterService converterService;
	
	/* by default sorting is in Ascending order, if you want to perform sorting in descending order than use below parameters with URL:
                    http://localhost:9091/user-management/userinfo/alluser?sort=userId,desc
                    http://localhost:9091/user-management/userinfo/alluser?page=1&size=4&sort=userId,desc
        */		    
	
	@GetMapping(value = "/alluser", produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<User> getUserinfo(@PageableDefault(page=0, size=4) Pageable pageRequest) {
		Page<User> userPage = userService.getAllUserInfo(pageRequest);
		List<User> userResultList = userPage.getContent();
		userResultList = userResultList.stream().map(userObj -> {
			converterService.convertToDto(userObj);
			return userObj;
		}).collect(Collectors.toList());
		// alternate way through method reference operator
		//userResultList.stream().map(converterService::convertToDto).collect(Collectors.toList());
		Page<User> userDtoPage = new PageImpl<>(userResultList, pageRequest, userPage.getTotalElements());
		 return userDtoPage;
	}

// if you don't want page attributes in the response
/*	@GetMapping(value = "/alluser", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getUserinfo(@PageableDefault(page=0, size=4) Pageable pageRequest) {
		Page<User> userPage = userService.getAllUserInfo(pageRequest);
		List<User> userResultList = userPage.getContent();
		userResultList = userResultList.stream().map(userObj -> {
			converterService.convertToDto(userObj);
			return userObj;
		}).collect(Collectors.toList());
		// alternate way through method reference operator
		//userResultList.stream().map(converterService::convertToDto).collect(Collectors.toList());
		 return userResultList;
	}
*/	
}
