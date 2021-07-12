package com.devsuperior.movieflix.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.devsuperior.movieflix.entities.Review;

public class ReviewDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotBlank(message = "The text field is required!")
	private String text;
	private UserReviewDTO user;
	
	
	public ReviewDTO() {
		
	}

	public ReviewDTO(Long id, String text, UserReviewDTO user) {
		this.id = id;
		this.text = text;
		this.user = user;
	}
	
	public ReviewDTO(Review entity) {
		this.id = entity.getId();
		this.text = entity.getText();
		this.user = new UserReviewDTO(entity.getUser());
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
	
	public UserReviewDTO getUser() {
		return user;
	}
	
	public void setUser(UserReviewDTO user) {
		this.user = user;
	}

}
