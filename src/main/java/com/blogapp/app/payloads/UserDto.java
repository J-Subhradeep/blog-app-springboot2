package com.blogapp.app.payloads;

import com.blogapp.app.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {

	private String name;
	private String email;
	private String password;
	private String about;

}
