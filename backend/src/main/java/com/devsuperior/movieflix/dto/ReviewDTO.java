package com.devsuperior.movieflix.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.devsuperior.movieflix.entities.Review;

public class ReviewDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotBlank(message = "The text field is required!")
	private String text;
	private MovieDTO movie;
	
	public ReviewDTO() {
		
	}

	public ReviewDTO(Long id, String text, MovieDTO movie) {
		this.id = id;
		this.text = text;
		this.movie = movie;
	}
	
	public ReviewDTO(Review entity) {
		this.id = entity.getId();
		this.text = entity.getText();
		this.movie = new MovieDTO(entity.getMovie());
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
}
