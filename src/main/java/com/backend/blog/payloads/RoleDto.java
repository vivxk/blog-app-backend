package com.backend.blog.payloads;

import javax.persistence.Id;

import lombok.Data;

@Data
public class RoleDto {
	@Id
	private int id;
	private String name;

}
