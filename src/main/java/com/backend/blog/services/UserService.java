package com.backend.blog.services;

import java.util.List;

import com.backend.blog.payloads.UserDto;

public interface UserService {
	UserDto createUser(UserDto user); // UserDto to avoid giving User entity directly. Instead passing to service
										// through dto.

	UserDto UpdateUser(UserDto user, Integer userId);

	UserDto getUserById(Integer userId);

	List<UserDto> getAllUsers();

	void deleteUser(Integer userId);
}
