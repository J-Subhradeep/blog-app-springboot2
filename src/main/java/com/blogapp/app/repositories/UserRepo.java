package com.blogapp.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapp.app.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	
}
