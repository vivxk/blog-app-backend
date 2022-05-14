package com.backend.blog.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;
	@NotEmpty(message = "Username cannot be empty!")
	@Size(min = 4, max = 20, message = "Username must be between 4 to 20 characters!")
	private String name;

	@Email(message = "Invalid Email address!")
	private String email;
	@NotEmpty(message = "Password cannot be blank!")
	@Size(min = 8, max = 16, message = "Password must be between 8 to 16 characters!")
	private String password;
	@NotEmpty
	private String about;

}
