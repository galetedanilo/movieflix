package com.devsuperior.moveflix.dto;

import javax.validation.constraints.NotBlank;

import com.devsuperior.moveflix.entities.User;

public class UserFullDTO extends UserDTO {

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "The password field is required!")
	private String password;
	
	public UserFullDTO() {
		super();
	}

	public UserFullDTO(Long id, String name, String email, String password) {
		super(id, name, email);
		this.password = password;
	}

	public UserFullDTO(User entity) {
		super(entity);
		password = entity.getPassword();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
