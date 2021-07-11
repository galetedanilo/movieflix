package com.devsuperior.moveflix.dto;

import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.NotBlank;

import com.devsuperior.moveflix.entities.Genre;

public class GenreDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	@NotBlank(message = "The name field is required!")
	private String name;
	private Instant createdAt;
	private Instant updateAt;
	
	public GenreDTO() {
		
	}
	
	public GenreDTO(Long id, String name, Instant createdAt, Instant updateAt) {
		this.id = id;
		this.name = name;
		this.createdAt = createdAt;
		this.updateAt = updateAt;
	}

	public GenreDTO(Genre entity) {
		id = entity.getId();
		name = entity.getName();
		createdAt = entity.getCreatedAt();
		updateAt = entity.getUpdateAt();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Instant getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Instant updateAt) {
		this.updateAt = updateAt;
	}
	
}
