package com.blogapp.app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blogapp.app.payloads.UserDto;


@Service
public interface UserService {

//	public User createUser(User user)
	public UserDto createUser(UserDto u);
	public UserDto updateUser(UserDto u, Integer userId);
	public UserDto getUserById(Integer userId);
	public void deleteUser(Integer userId);
	List<UserDto> getAllUsers();
}
