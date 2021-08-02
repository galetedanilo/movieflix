package com.devsuperior.movieflix.dto;

import java.io.Serializable;

import com.devsuperior.movieflix.entities.Review;

public class ReviewMinDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String text;
	private String username;
	private Long movieId;
	private UserDTO user;
	
	public ReviewMinDTO() {
		
	}
	
	public ReviewMinDTO(Long id, String text, String username, Long movieId, UserDTO user) {
		this.id = id;
		this.text = text;
		this.username = username;
		this.movieId = movieId;
		this.user = user;
	}
	
	public ReviewMinDTO(Review entity) {
		id = entity.getId();
		text = entity.getText();
		movieId = entity.getMovie().getId();
		
		user = new UserDTO(entity.getUser());
		
		username = user.getName();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}
	
}
