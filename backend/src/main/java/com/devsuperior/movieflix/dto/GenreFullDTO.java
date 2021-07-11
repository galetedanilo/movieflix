package com.devsuperior.movieflix.dto;

import java.time.Instant;

import com.devsuperior.movieflix.entities.Genre;

public class GenreFullDTO extends GenreDTO {

	private static final long serialVersionUID = 1L;

	private Instant createdAt;
	private Instant updateAt;
	
	public GenreFullDTO() {
		super();
	}

	public GenreFullDTO(Long id, String name, Instant createdAt, Instant updateAt) {
		super(id, name);
		this.createdAt = createdAt;
		this.updateAt = updateAt;
	}
	
	public GenreFullDTO(Genre entity) {
		super(entity);
		this.createdAt = entity.getCreatedAt();
		this.updateAt = entity.getUpdateAt();
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
