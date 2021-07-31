package com.devsuperior.movieflix.dto;

import java.io.Serializable;

import com.devsuperior.movieflix.entities.Review;

public class ReviewMinDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String text;
	private String username;
	private Long movieId;
	
	public ReviewMinDTO() {
		
	}
	
	public ReviewMinDTO(String text, String username, Long movieId) {
		this.text = text;
		this.username = username;
		this.movieId = movieId;
	}
	
	public ReviewMinDTO(Review entity) {
		text = entity.getText();
		username = entity.getUser().getName();
		movieId = entity.getMovie().getId();
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
	
}
