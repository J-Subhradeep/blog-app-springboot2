package com.blogapp.app.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	private Integer id;
	
	@NotNull
	@NotEmpty
	@Size(min=4,message = "Username must be min of 4 chars")
	private String name;
	@Email
	private String email;
	@NotNull
	private String password;
	@NotNull
	private String about;

}
