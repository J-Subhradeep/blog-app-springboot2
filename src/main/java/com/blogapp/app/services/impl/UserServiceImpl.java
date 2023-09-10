package com.blogapp.app.services.impl;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapp.app.entities.User;
import com.blogapp.app.payloads.UserDto;
import com.blogapp.app.repositories.UserRepo;
import com.blogapp.app.services.UserService;
import com.blogapp.app.exceptions.*;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto u) {
		// TODO Auto-generated method stub
		User user = this.dtoToUser(u);
		User savedUser = this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto u, Integer userId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		user.setName(u.getName());
		user.setEmail(u.getEmail());
		user.setAbout(u.getAbout());
		User updatedUser = this.userRepo.save(user);
		
		return this.userToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","id", userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> user = this.userRepo.findAll();
		List<UserDto> dtos  = user.stream().map(u->this.userToDto(u)).collect(Collectors.toList());
		
		
		return dtos;
	}
	
	private User dtoToUser(UserDto u) {
//		User user = new User();
//		user.setName(u.getName());
//		user.setEmail(u.getEmail());
//		user.setPassword(u.getPassword());
//		user.setAbout(u.getAbout());
		
		User user = this.modelMapper.map(u, User.class);
		return user;
	}
	private UserDto userToDto(User u) {
//		UserDto user = new UserDto();
//		user.setName(u.getName());
//		user.setEmail(u.getEmail());
//		user.setPassword(u.getPassword());
//		user.setAbout(u.getAbout());
		
		UserDto user = this.modelMapper.map(u, UserDto.class);
		return user;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","id", userId));
		this.userRepo.delete(user);
	
	}
}
