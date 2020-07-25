package com.springboot.rest.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.rest.dto.UserDto;
import com.springboot.rest.entity.User;

@Component
public class ConverterService {
	
	@Autowired
	private ModelMapper modelMapper;

// convert entity to dto	
	public UserDto convertToDto(User userObject) {
		return modelMapper.map(userObject, UserDto.class);
	}

// convert dto to entity
	public User convertToEntity(UserDto userdto) {
		return modelMapper.map(userdto, User.class);
	}

}
