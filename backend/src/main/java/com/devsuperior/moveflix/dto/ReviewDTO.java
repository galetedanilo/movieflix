package com.devsuperior.moveflix.dto;

import java.io.Serializable;

import com.devsuperior.moveflix.entities.Review;

public class ReviewDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String text;
	private MovieDTO movie;
	private UserDTO user;
	
	public ReviewDTO() {
		
	}

	public ReviewDTO(Long id, String text, MovieDTO movie, UserDTO user) {
		this.id = id;
		this.text = text;
		this.movie = movie;
		this.user = user;
	}
	
	public ReviewDTO(Review entity) {
		this.id = entity.getId();
		this.text = entity.getText();
		this.movie = new MovieDTO(entity.getMovie());
		this.user = new UserDTO(entity.getUser());
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

	public MovieDTO getMovie() {
		return movie;
	}

	public void setMovie(MovieDTO movie) {
		this.movie = movie;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

}
